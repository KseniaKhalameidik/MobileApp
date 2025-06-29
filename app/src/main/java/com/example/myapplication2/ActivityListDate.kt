package com.example.myapplication2

sealed class ActivityListDate {
    data class Section(val title: String) : ActivityListDate()
    data class Activity(
        val id: Int,
        val distance: String,
        val time: String,
        val type: String,
        val user: String?,
        val date: String,
        val startTime: String? = null,
        val finishTime: String? = null,
        val comment: String? = null
    ) : ActivityListDate()
}