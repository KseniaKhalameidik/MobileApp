package com.example.myapplication2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ActivityDao {
    @Query("SELECT * FROM activities ORDER BY startTime DESC")
    fun getAllActivities(): LiveData<List<ActivityEntity>>

    @Query("DELETE FROM activities WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Insert
    suspend fun insert(activity: ActivityEntity)
}