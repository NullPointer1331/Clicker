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
    ): View? {
        _binding = FragmentClickerBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val database = ClickerDatabase.getInstance(application)
        val playerDao = database.playerDao
        val shopItemDao = database.shopItemDao
        val viewModelFactory = ClickerViewModelFactory(playerDao, shopItemDao)
        viewModel = ViewModelProvider(this.requireActivity(), viewModelFactory)[ClickerViewModel::class.java]

        binding.button.setOnClickListener {
            viewModel.click()
        }
        viewModel.points.observe(viewLifecycleOwner, {
            binding.pointsView.text = "Points: $it"
        })

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