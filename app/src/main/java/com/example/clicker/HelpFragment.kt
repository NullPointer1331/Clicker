package com.example.clicker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.clicker.databinding.FragmentHelpBinding
import com.example.clicker.databinding.FragmentStoreBinding

class HelpFragment : Fragment() {
    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ClickerViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this.requireActivity())[ClickerViewModel::class.java]

        binding.resetButton.setOnClickListener {
            viewModel.reset()
        }

        return view
    }
}