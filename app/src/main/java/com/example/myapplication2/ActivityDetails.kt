package com.example.myapplication2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch

class ActivityDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity_details)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.inflateMenu(R.menu.menu_activity_details)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_share -> {
                    true
                }
                R.id.action_delete -> {
                    deleteActivity()
                    true
                }
                else -> false
            }
        }

        val distance = intent.getStringExtra("distance") ?: ""
        val time = intent.getStringExtra("time") ?: ""
        val type = intent.getStringExtra("type") ?: ""
        toolbar.title = type
        val date = intent.getStringExtra("date") ?: ""
        val user = intent.getStringExtra("user")
        val userView = findViewById<TextView>(R.id.activityDetailsUser)
        if (!user.isNullOrBlank()) {
            userView.text = user
            userView.visibility = View.VISIBLE
        } else {
            userView.visibility = View.GONE
        }
        val startTime = intent.getStringExtra("start") ?: ""
        val finishTime = intent.getStringExtra("finish") ?: ""
        val comment = intent.getStringExtra("comment") ?: ""


        findViewById<TextView>(R.id.detailsDistance).text = distance
        findViewById<TextView>(R.id.detailsTime).text = time
        findViewById<TextView>(R.id.detailsDate).text = date
        findViewById<TextView>(R.id.detailsStartTime).text = startTime
        findViewById<TextView>(R.id.detailsEndTime).text = finishTime
        findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.detailsComment).setText(comment)

    }

    private fun deleteActivity() {
        val id = intent.getIntExtra("activity_id", -1)
        if (id != -1) {
            val db = DatabaseProvider.getDatabase(this)
            lifecycleScope.launch {
                db.activityDao().deleteById(id)
                finish()
            }
        }
    }
}
