package com.example.myapplication2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class Activity: AppCompatActivity() {
    companion object {
        private const val TAG_ACTIVITY = "fragment_activity"
        private const val TAG_PROFILE = "fragment_profile"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, ActivityFragment(), TAG_ACTIVITY)
                .commit()
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_activity -> {
                    showFragment(TAG_ACTIVITY, ActivityFragment())
                    true
                }
                R.id.nav_profile -> {
                    showFragment(TAG_PROFILE, ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun showFragment(tag: String, fragment: androidx.fragment.app.Fragment) {
        val fragmentManager = supportFragmentManager
        val activityFragment = fragmentManager.findFragmentByTag(TAG_ACTIVITY)
        val profileFragment = fragmentManager.findFragmentByTag(TAG_PROFILE)

        val transaction = fragmentManager.beginTransaction()

        activityFragment?.let { transaction.hide(it) }
        profileFragment?.let { transaction.hide(it) }

        var targetFragment = fragmentManager.findFragmentByTag(tag)
        if (targetFragment == null) {
            targetFragment = fragment
            transaction.add(R.id.fragmentContainer, targetFragment, tag)
        } else {
            transaction.show(targetFragment)
        }

        transaction.commit()
    }
}