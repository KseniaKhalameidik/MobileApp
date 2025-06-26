package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UsersTabFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ActivityAdapter(getItems()) { activity ->
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
                type = "Серфинг 🏄",
                user = "@van_darkholme",
                date = "14 часов назад"
            ),
            ActivityListDate.Activity(
                distance = "228 м",
                time = "14 часов 48 минут",
                type = "Качели",
                user = "@techniquepasha",
                date = "14 часов назад"
            ),
            ActivityListDate.Activity(
                distance = "10 км",
                time = "1 час 10 минут",
                type = "Езда на кадилак",
                user = "@morgen_shtern",
                date = "14 часов назад"
            )
        )
    }
}