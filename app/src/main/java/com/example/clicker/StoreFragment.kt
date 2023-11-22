package com.example.clicker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.clicker.databinding.FragmentStoreBinding

class StoreFragment : Fragment() {
    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ClickerViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val database = ClickerDatabase.getInstance(application)
        val playerDao = database.playerDao
        val shopItemDao = database.shopItemDao
        val viewModelFactory = ClickerViewModelFactory(playerDao, shopItemDao)
        viewModel = ViewModelProvider(this, viewModelFactory)[ClickerViewModel::class.java]

        viewModel.points.observe(viewLifecycleOwner, {
            binding.pointsView.text = "Points: $it"
        })
        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}