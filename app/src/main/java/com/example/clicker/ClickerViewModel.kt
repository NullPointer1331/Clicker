package com.example.clicker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ClickerViewModel(val playerDao: PlayerDao, val shopItemDao: ShopItemDao) : ViewModel() {
    var player: Player
    val points: MutableLiveData<Double>

    init {
        player = Player()
        points = MutableLiveData(player.points)
        viewModelScope.launch {
            val existingPlayer = playerDao.getPlayer(1)
            if (existingPlayer != null) {
                player = existingPlayer
            } else {
                playerDao.insertPlayer(player)
            }
            points.value = player.points

            val existingItems = shopItemDao.getAll()
            if (existingItems != null) {
                player.items = existingItems.toMutableList()
            } else {
                player.items.add(ShopItem(1, "Better Mouse", 10.0, 0.0, 1.0))
                player.items.add(ShopItem(2, "Automation", 100.0, 1.0, 0.0))
                player.items.add(ShopItem(3, "Better Automation", 1000.0, 5.0, 0.0))
                player.items.add(ShopItem(4, "Ultra Click", 5000.0, 0.0, 3.0, 0.0, 0.1))
                player.items.add(ShopItem(5, "Ultra Automation", 10000.0, 3.0, 0.0, 0.1))
                for (item in player.items) {
                    shopItemDao.insertShopItem(item)
                }
            }
            player.setStats()
        }
    }

    fun click() {
        player.points += player.getPointsPerClick()
        points.value = player.points
        viewModelScope.launch {
            playerDao.updatePlayer(player)
        }
    }

    fun passSecond() {
        player.points += player.getPointsPerSecond()
        points.value = player.points
    }

    fun buyItem(item: ShopItem) {
        if (player.points >= item.cost) {
            player.points -= item.cost
            points.value = player.points
            item.level++
            player.setStats()
        }
    }
}