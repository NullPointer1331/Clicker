package com.example.clicker
import androidx.room.*

@Entity
data class ShopItem(@PrimaryKey val Id: Int,
                    val name: String, var cost: Double, val pointsPerSecond: Double = 0.0,
                    val pointsPerClick: Double = 0.0, val pointsPerSecondMult: Double = 0.0,
                    val pointsPerClickMult: Double = 0.0, var level : Int = 0) {
    fun getEffects() : String {
        var effects = ""
        if (pointsPerClick != 0.0) {
            effects += "Points Per Click: $pointsPerClick\n"
        }
        if (pointsPerSecond != 0.0) {
            effects += "Points Per Second: $pointsPerSecond\n"
        }
        if (pointsPerClickMult != 0.0) {
            effects += "Points Per Click Multiplier: $pointsPerClickMult\n"
        }
        if (pointsPerSecondMult != 0.0) {
            effects += "Points Per Second Multiplier: $pointsPerSecondMult\n"
        }
        return effects
    }
}

@Dao interface ShopItemDao {
    @Query("SELECT * FROM shopitem")
    suspend fun getAll(): List<ShopItem>
    @Query("SELECT * FROM shopitem WHERE Id = :Id")
    suspend fun getShopItem(Id: Int): ShopItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShopItem(shopItem: ShopItem)

    @Update
    suspend fun updateShopItem(shopItem: ShopItem)

    @Delete
    suspend fun deleteShopItem(shopItem: ShopItem)
}
