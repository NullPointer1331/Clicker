package com.example.clicker

import androidx.room.*

@Entity
data class Player(@PrimaryKey var Id: Int,
                  var points: Double = 0.0,
                  var basePointsPerClick: Double = 1.0,
                  var pointsPerClickMult: Double = 1.0,
                  var basePointsPerSecond: Double = 0.0,
                  var pointsPerSecondMult: Double = 1.0,
                  var items: MutableList<ShopItem> = mutableListOf<ShopItem>()) {

    fun setStats() {
        basePointsPerClick = 1.0
        pointsPerClickMult = 1.0
        basePointsPerSecond = 0.0
        pointsPerSecondMult = 1.0
        for (item in items) {
            basePointsPerClick += item.pointsPerClick * item.level
            pointsPerClickMult += item.pointsPerClickMult * item.level
            basePointsPerSecond += item.pointsPerSecond * item.level
            pointsPerSecondMult += item.pointsPerSecondMult * item.level
        }
    }
    fun buyItem(item: ShopItem) {
        if (points >= item.cost) {
            points -= item.cost
            item.level++
            setStats()
        }
    }
    fun getPointsPerClick(): Double {
        return basePointsPerClick * pointsPerClickMult
    }
    fun getPointsPerSecond(): Double {
        return basePointsPerSecond * pointsPerSecondMult
    }
}

@Dao interface PlayerDao {
    @Query("SELECT * FROM player WHERE Id = :Id")
    fun getPlayer(Id: Int): Player

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player: Player)

    @Update
    fun updatePlayer(player: Player)

    @Delete
    fun deletePlayer(player: Player)
}
