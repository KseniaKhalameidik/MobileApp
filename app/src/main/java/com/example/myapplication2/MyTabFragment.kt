package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MyTabFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val emptyState = view.findViewById<View>(R.id.emptyState)
        val db = DatabaseProvider.getDatabase(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ActivityAdapter(emptyList()) { activity ->
            val intent = Intent(requireContext(), ActivityDetails::class.java)
            intent.putExtra("distance", activity.distance)
            intent.putExtra("time", activity.time)
            intent.putExtra("type", activity.type)
            intent.putExtra("date", activity.date)
            intent.putExtra("user", activity.user)
            intent.putExtra("start", activity.startTime)
            intent.putExtra("finish", activity.finishTime)
            intent.putExtra("comment", activity.comment)
            intent.putExtra("activity_id", activity.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        db.activityDao().getAllActivities().observe(viewLifecycleOwner) { activities ->
            if (activities.isNullOrEmpty()) {
                recyclerView.visibility = View.GONE
                emptyState.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                emptyState.visibility = View.GONE

                val grouped = activities.groupBy {
                    val date = Date(it.startTime)
                    SimpleDateFormat("LLLL yyyy", Locale("ru")).format(date)
                }

                val list = mutableListOf<ActivityListDate>()
                for ((sectionTitle, acts) in grouped) {
                    
                    list.add(ActivityListDate.Section(sectionTitle.replaceFirstChar { it.uppercase() }))

                    acts.forEach { itEntity ->
                        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                        val dateString = dateFormat.format(Date(itEntity.startTime))
                        val durationMillis = itEntity.endTime - itEntity.startTime
                        val hours = TimeUnit.MILLISECONDS.toHours(durationMillis)
                        val minutes = TimeUnit.MILLISECONDS.toMinutes(durationMillis) % 60
                        val seconds = TimeUnit.MILLISECONDS.toSeconds(durationMillis) % 60
                        val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                        val distance = "${itEntity.coordinates.size * 0.5} км"
                        val startString = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(itEntity.startTime))
                        val finishString = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(itEntity.endTime))

                        list.add(
                            ActivityListDate.Activity(
                                id = itEntity.id,
                                distance = distance,
                                time = timeString,
                                type = itEntity.type.displayName,
                                user = null,
                                date = dateString,
                                startTime = startString,
                                finishTime = finishString,
                                comment = ""
                            )
                        )
                    }
                }
                adapter.setData(list)
            }
        }
    }
}