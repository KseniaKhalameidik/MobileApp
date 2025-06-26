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
        recyclerView.layoutManager = LinearLayoutManager(context)
        val items = getItems()
        recyclerView.adapter = ActivityAdapter(items) { activity ->
            val intent = Intent(requireContext(), ActivityDetails::class.java)
            intent.putExtra("distance", activity.distance)
            intent.putExtra("time", activity.time)
            intent.putExtra("type", activity.type)
            intent.putExtra("date", activity.date)
            intent.putExtra("user", activity.user)
            intent.putExtra("start", activity.startTime)
            intent.putExtra("finish", activity.finishTime)
            intent.putExtra("comment", activity.comment)
            startActivity(intent)
        }
    }

    private fun getItems(): List<ActivityListDate> {
        return listOf(
            ActivityListDate.Section("Вчера"),
            ActivityListDate.Activity(
                distance = "14.32 км",
                time = "2 часа 46 минут",
                type = "Серфинг",
                user = null,
                date = "14 часов назад"
            ),
            ActivityListDate.Section("Май 2022 года"),
            ActivityListDate.Activity(
                distance = "1 000 м",
                time = "60 минут",
                type = "Велосипед",
                user = null,
                date = "29.05.2022"
            )
        )
    }
}