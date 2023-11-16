package com.example.clicker

import androidx.lifecycle.ViewModel

class ClickerViewModel : ViewModel() {
    var player: Player
    private lateinit var shopItems: List<ShopItem>
    private lateinit var shopItemDao: ShopItemDao
    private lateinit var playerDao: PlayerDao
    private lateinit var appDatabase: AppDatabase

    init {
        /*
        appDatabase = AppDatabase.getDatabase()
        shopItemDao = appDatabase.shopItemDao()
        playerDao = appDatabase.playerDao()
        shopItems = shopItemDao.getAllItems()
        player = playerDao.getPlayer(1)
        player.setStats()*/
        player = Player()
    }

    fun click() {
        player.points += player.getPointsPerClick()
    }

    fun passSecond() {
        player.points += player.getPointsPerSecond()
    }
}