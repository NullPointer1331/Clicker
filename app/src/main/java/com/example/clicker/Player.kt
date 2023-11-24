package com.example.clicker

import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Entity
data class Player(@PrimaryKey var Id: Int = 1,
                  var points: Double = 0.0,
                  var basePointsPerClick: Double = 1.0,
                  var pointsPerClickMult: Double = 1.0,
                  var basePointsPerSecond: Double = 0.0,
                  var pointsPerSecondMult: Double = 1.0,
                  @Ignore
                  var items: MutableLiveData<MutableList<ShopItem>> = MutableLiveData(mutableListOf<ShopItem>())) {

    fun setStats() {
        basePointsPerClick = 1.0
        pointsPerClickMult = 1.0
        basePointsPerSecond = 0.0
        pointsPerSecondMult = 1.0
        for (item in items.value!!) {
            basePointsPerClick += item.pointsPerClick * item.level
            pointsPerClickMult += item.pointsPerClickMult * item.level
            basePointsPerSecond += item.pointsPerSecond * item.level
            pointsPerSecondMult += item.pointsPerSecondMult * item.level
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
    suspend fun getPlayer(Id: Int): Player?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: Player)

    @Update
    suspend fun updatePlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)
}
