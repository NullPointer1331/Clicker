package com.example.clicker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.clicker.databinding.FragmentClickerBinding

class ClickerFragment : Fragment() {
    private var _binding: FragmentClickerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ClickerViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClickerBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this.requireActivity())[ClickerViewModel::class.java]

        binding.button.setOnClickListener {
            viewModel.click()
        }
        viewModel.points.observe(viewLifecycleOwner) {
            binding.pointsView.text = "Points: $it"
        }
        viewModel.perClick.observe(viewLifecycleOwner) {
            binding.perClickView.text = "Points Per Click: $it"
        }
        viewModel.perSecond.observe(viewLifecycleOwner) {
            binding.perSecondView.text = "Points Per Second: $it"
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}