package com.example.clicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ClickerViewModelFactory(private val playerDao: PlayerDao, private val shopItemDao: ShopItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClickerViewModel::class.java)) {
            return ClickerViewModel(playerDao, shopItemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}