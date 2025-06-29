package com.example.myapplication2

import androidx.room.*
import java.util.*

@Entity(tableName = "activities")
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: ActivityType,
    val startTime: Long,
    val endTime: Long,
    val coordinates: List<LatLng>
)

data class LatLng(val latitude: Double, val longitude: Double)