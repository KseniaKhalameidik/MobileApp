package com.example.myapplication2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

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

        val types = listOf(
            ActivityType("Велосипед", R.drawable.welcome_screen_image),
            ActivityType("Бег", R.drawable.welcome_screen_image),
            ActivityType("Шаг", R.drawable.welcome_screen_image)
        )

        val adapter = ActivityTypeAdapter(types) { type ->
            selectedType = type
        }
        recycler.adapter = adapter

        view.findViewById<MaterialButton>(R.id.startButton).setOnClickListener {
            val trackingFragment = TrackingFragment()
            val args = Bundle()
            args.putString("type", selectedType?.name ?: "Велосипед")
            trackingFragment.arguments = args

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, trackingFragment)
                .addToBackStack(null)
                .commit()
        }

        val toolbar = view.findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.back)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}