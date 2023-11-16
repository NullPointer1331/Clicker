package com.example.clicker
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Player::class, ShopItem::class), version = 1, exportSchema = false)
abstract class ClickerDatabase : RoomDatabase() {
    abstract val playerDao: PlayerDao
    abstract val shopItemDao: ShopItemDao
    companion object {
        @Volatile
        private var INSTANCE: ClickerDatabase? = null
        fun getInstance(context: Context): ClickerDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ClickerDatabase::class.java, "clicker.db").build()
                }
            }
            return INSTANCE!!
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}