package com.example.clicker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class ClickerViewModel(val playerDao: PlayerDao, val shopItemDao: ShopItemDao) : ViewModel() {
    var player: Player
    val points: MutableLiveData<Double>
    val perClick: MutableLiveData<Double>
    val perSecond: MutableLiveData<Double>

    init {
        player = Player()
        points = MutableLiveData(player.points)
        perClick = MutableLiveData(player.getPointsPerClick())
        perSecond = MutableLiveData(player.getPointsPerSecond())
        viewModelScope.launch {
            val existingPlayer = playerDao.getPlayer(1)
            if (existingPlayer != null) {
                player = existingPlayer
            } else {
                playerDao.insertPlayer(player)
            }
            points.value = player.points

            val existingItems = shopItemDao.getAll()
            if (existingItems.isNullOrEmpty()) {
                setItems()
            } else {
                player.items.value = existingItems.toMutableList()
            }
            player.setStats()
            perClick.value = player.getPointsPerClick()
            perSecond.value = player.getPointsPerSecond()
        }
    }

    fun reset() {
        player = Player()
        points.value = player.points
        perClick.value = player.getPointsPerClick()
        perSecond.value = player.getPointsPerSecond()
        viewModelScope.launch {
            playerDao.updatePlayer(player)
            setItems()
        }
    }
    private fun setItems() {
        player.items.value = mutableListOf(
        ShopItem(1, "Better Mouse", 10.0, 0.0, 1.0),
        ShopItem(2, "Automation", 100.0, 1.0),
        ShopItem(3, "Super Click", 500.0, 0.0, 5.0),
        ShopItem(4, "Better Automation", 1000.0, 5.0, 0.0),
        ShopItem(5, "Ultra Click", 5000.0, 0.0, 3.0, 0.0, 0.1),
        ShopItem(6, "Ultra Automation", 10000.0, 3.0, 0.0, 0.1))
        viewModelScope.launch {
            for (item in shopItemDao.getAll()) {
                shopItemDao.deleteShopItem(item)
            }
            for (item in player.items.value!!) {
                shopItemDao.insertShopItem(item)
            }
        }
    }

    fun click() {
        player.points += player.getPointsPerClick()
        player.points = (player.points * 100.0).roundToInt() / 100.0
        points.value = player.points
        viewModelScope.launch {
            playerDao.updatePlayer(player)
        }
    }

    fun passSecond() {
        player.points += player.getPointsPerSecond()
        player.points = (player.points * 100.0).roundToInt() / 100.0
        points.value = player.points
        viewModelScope.launch {
            playerDao.updatePlayer(player)
        }
    }

    fun buyItem(item: ShopItem) {
        if (player.points >= item.getFullCost()) {
            player.points -= item.getFullCost()
            player.points = (player.points * 100.0).roundToInt() / 100.0
            points.value = player.points
            item.level++
            player.setStats()
            perClick.value = player.getPointsPerClick()
            perSecond.value = player.getPointsPerSecond()
            viewModelScope.launch {
                playerDao.updatePlayer(player)
                shopItemDao.updateShopItem(item)
            }
        }
    }

    fun buyMax(item: ShopItem) {
        while (player.points >= item.getFullCost()) {
            player.points -= item.getFullCost()
            player.points = (player.points * 100.0).roundToInt() / 100.0
            points.value = player.points
            item.level++
        }
        player.setStats()
        perClick.value = player.getPointsPerClick()
        perSecond.value = player.getPointsPerSecond()
        viewModelScope.launch {
            playerDao.updatePlayer(player)
            shopItemDao.updateShopItem(item)
        }
    }
}