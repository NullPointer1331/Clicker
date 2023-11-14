package com.example.clicker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.clicker.databinding.FragmentClickerBinding

class ClickerFragment : Fragment() {
    private var _binding: FragmentClickerBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClickerBinding.inflate(inflater, container, false)
        val view = binding.root
        val player = Player()

        binding.button.setOnClickListener {
            player.points += player.getPointsPerClick()
            binding.pointsView.text = "Points: ${player.points}"
        }

        binding.perClickView.text = "Points Per Click: ${player.getPointsPerClick()}"
        binding.perSecondView.text = "Points Per Second: ${player.getPointsPerSecond()}"
        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}