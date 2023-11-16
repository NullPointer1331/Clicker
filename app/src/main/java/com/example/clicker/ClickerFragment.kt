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

        /*
        val application = requireNotNull(this.activity).application
        val playerDao = ClickerDatabase.getInstance(application).playerDao
        val shopItemDao = ClickerDatabase.getInstance(application).shopItemDao
        val viewModelFactory = ClickerViewModelFactory(playerDao, shopItemDao)
        viewModel = ViewModelProvider(this, viewModelFactory)[ClickerViewModel::class.java]
         */
        viewModel = ViewModelProvider(this)[ClickerViewModel::class.java]

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