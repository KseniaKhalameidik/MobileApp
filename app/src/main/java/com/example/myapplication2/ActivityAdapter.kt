package com.example.myapplication2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActivityAdapter(
    private val items: List<ActivityListDate>,
    private val onActivityClick: (ActivityListDate.Activity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_SECTION = 0
        private const val VIEW_TYPE_ACTIVITY = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ActivityListDate.Section -> VIEW_TYPE_SECTION
            is ActivityListDate.Activity -> VIEW_TYPE_ACTIVITY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SECTION -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.date_section, parent, false)
                SectionViewHolder(view)
            }
            VIEW_TYPE_ACTIVITY -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.date_activity, parent, false)
                ActivityViewHolder(view, onActivityClick)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ActivityListDate.Section -> (holder as SectionViewHolder).bind(item)
            is ActivityListDate.Activity -> (holder as ActivityViewHolder).bind(item)
        }
    }

    class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ActivityListDate.Section) {
            itemView.findViewById<TextView>(R.id.sectionTitle).text = item.title
        }
    }

    class ActivityViewHolder(
        view: View,
        private val onClick: (ActivityListDate.Activity) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: ActivityListDate.Activity) {
            itemView.findViewById<TextView>(R.id.activityDistance).text = item.distance
            itemView.findViewById<TextView>(R.id.activityTime).text = item.time
            itemView.findViewById<TextView>(R.id.activityType).text = item.type

            val userView = itemView.findViewById<TextView>(R.id.activityUser)
            if (item.user != null) {
                userView.text = item.user
                userView.visibility = View.VISIBLE
            } else {
                userView.visibility = View.GONE
            }

            itemView.findViewById<TextView>(R.id.activityDate).text = item.date

            itemView.setOnClickListener { onClick(item) }
        }
    }
}