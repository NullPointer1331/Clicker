package com.example.clicker
import androidx.room.*

@Database(entities = [Player::class, ShopItem::class], version = 1)
abstract class AppDatabase{
    abstract fun playerDao(): PlayerDao
    abstract fun shopItemDao(): ShopItemDao
}