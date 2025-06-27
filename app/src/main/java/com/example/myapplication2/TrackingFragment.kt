package com.example.myapplication2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton

class TrackingFragment : Fragment() {

    private var isRunning = true
    private var elapsedSeconds = 0L
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var timeView: TextView
    private lateinit var pauseButton: MaterialButton

    private val timerRunnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                elapsedSeconds++
                updateTime()
            }
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tracking, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        timeView = view.findViewById(R.id.activityTime)
        pauseButton = view.findViewById(R.id.pauseButton)

        val type = arguments?.getString("type") ?: "Велосипед"
        val toolbar = view.findViewById<MaterialToolbar>(R.id.activityType)
        toolbar.title = type


        pauseButton.setOnClickListener {
            isRunning = !isRunning
            pauseButton.icon = if (isRunning)
                requireContext().getDrawable(R.drawable.ic_pause)
            else
                requireContext().getDrawable(R.drawable.baseline_play_24)
        }

        view.findViewById<MaterialButton>(R.id.finishButton).setOnClickListener {
            handler.removeCallbacks(timerRunnable)
        }

        updateTime()
        handler.post(timerRunnable)

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun updateTime() {
        val hours = elapsedSeconds / 3600
        val minutes = (elapsedSeconds % 3600) / 60
        val seconds = elapsedSeconds % 60
        timeView.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(timerRunnable)
    }
}