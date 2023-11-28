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
    private lateinit var viewModel: ClickerViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this.requireActivity())[ClickerViewModel::class.java]

        viewModel.points.observe(viewLifecycleOwner) {
            binding.pointsView.text = "Points: $it"
        }

        val adapter = ShopItemAdapter(viewModel)
        binding.itemList.adapter = adapter

        viewModel.player.items.observe(viewLifecycleOwner) {
            it?.let {
                adapter.data = it
            }
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}