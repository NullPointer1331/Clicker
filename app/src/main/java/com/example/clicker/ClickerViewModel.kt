package com.example.clicker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ClickerViewModel(/*val playerDao: PlayerDao, val shopItemDao: ShopItemDao*/) : ViewModel() {
    var player: Player
    val points: MutableLiveData<Double>

    init {
        /*
        appDatabase = AppDatabase.getDatabase()
        shopItemDao = appDatabase.shopItemDao()
        playerDao = appDatabase.playerDao()
        shopItems = shopItemDao.getAllItems()
        player = playerDao.getPlayer(1)
        player.setStats()*/
        player = Player()
        /*
        viewModelScope.launch {
            val existingPlayer = playerDao.getPlayer(1)
            if (existingPlayer != null) {
                player = existingPlayer
            } else {
                playerDao.insertPlayer(player)
            }
        }*/
        points = MutableLiveData(player.points)
    }

    fun click() {
        player.points += player.getPointsPerClick()
        points.value = player.points
    }

    fun passSecond() {
        player.points += player.getPointsPerSecond()
        points.value = player.points
    }
}