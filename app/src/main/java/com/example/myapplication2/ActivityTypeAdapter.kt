package com.example.myapplication2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActivityTypeAdapter(
    private val items: List<ActivityType>,
    private var selectedIndex: Int = 0,
    private val onSelect: (ActivityType) -> Unit
) : RecyclerView.Adapter<ActivityTypeAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.activityTypeName)
        val icon: ImageView = view.findViewById(R.id.activityTypeIcon)
        val root: View = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activity_type, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.icon.setImageResource(item.iconRes)
        holder.root.isSelected = position == selectedIndex

        holder.root.setOnClickListener {
            val prevIndex = selectedIndex
            selectedIndex = position
            notifyItemChanged(prevIndex)
            notifyItemChanged(selectedIndex)
            onSelect(item)
        }
    }
}