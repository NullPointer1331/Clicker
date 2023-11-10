package com.example.clicker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class ClickerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_clicker, container, false)
        val button = view.findViewById<Button>(R.id.button)
        val player = Player(0)
        player.basePointsPerSecond = 1.0
        val pointsDisplay = view.findViewById<TextView>(R.id.pointsView)
        button.setOnClickListener {
            player.points += player.getPointsPerClick()
            pointsDisplay.text = "Points: ${player.points}"
        }

        val perClick = view.findViewById<TextView>(R.id.perClickView)
        perClick.text = "Points Per Click: ${player.getPointsPerClick()}"
        val perSecond = view.findViewById<TextView>(R.id.perSecondView)
        perSecond.text = "Points Per Second: ${player.getPointsPerSecond()}"
        // Inflate the layout for this fragment
        return view
    }
}