package com.example.clicker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.clicker.databinding.FragmentClickerBinding

class ClickerFragment : Fragment() {
    private var _binding: FragmentClickerBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ClickerViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClickerBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[ClickerViewModel::class.java]
        val player = Player()

        binding.button.setOnClickListener {
            viewModel.click()
            binding.pointsView.text = "Points: ${viewModel.player.points}"
        }

        binding.perClickView.text = "Points Per Click: ${viewModel.player.getPointsPerClick()}"
        binding.perSecondView.text = "Points Per Second: ${viewModel.player.getPointsPerSecond()}"
        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}