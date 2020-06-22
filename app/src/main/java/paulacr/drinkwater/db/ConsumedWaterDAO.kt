package paulacr.drinkwater.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import paulacr.drinkwater.data.ConsumedWater

@Dao
interface ConsumedWaterDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg consumedWater: ConsumedWater)

    @Insert
    suspend fun insert(consumedWater: ConsumedWater)

    @Query("SELECT * FROM consumed_water")
    suspend fun getAll(): List<ConsumedWater>
}
