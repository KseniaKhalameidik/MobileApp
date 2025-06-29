package com.example.myapplication2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class StartActivityFragment : Fragment() {

    private var selectedType: ActivityType? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.activityTypeRecycler)
        recycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        val types = ActivityType.values().toList()

        val adapter = ActivityTypeAdapter(types) { type ->
            selectedType = type
        }
        recycler.adapter = adapter

        view.findViewById<MaterialButton>(R.id.startButton).setOnClickListener {
            val args = Bundle()
            args.putString("type", selectedType?.displayName ?: ActivityType.BIKE.displayName)
            val db = DatabaseProvider.getDatabase(requireContext())
            val type = selectedType ?: ActivityType.BIKE
            val now = System.currentTimeMillis()
            val sevenDaysMillis = 1000L * 60 * 60 * 24 * 7
            val startTime = now - (0..sevenDaysMillis).random()

            val durationMillis = (1000L * 60 * (5..120).random())
            val endTime = startTime + durationMillis

            val coordinates = List((5..20).random()) {
                LatLng(
                    latitude = 55.0 + Math.random(),
                    longitude = 37.0 + Math.random()
                )
            }

            val activity = ActivityEntity(
                type = type,
                startTime = startTime,
                endTime = endTime,
                coordinates = coordinates
            )
            lifecycleScope.launch {
                db.activityDao().insert(activity)
                // После сохранения — переход на следующий экран
                val trackingFragment = TrackingFragment()
                val args = Bundle()
                args.putString("type", (selectedType ?: ActivityType.BIKE).displayName)
                trackingFragment.arguments = args

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, trackingFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        val toolbar = view.findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.back)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}