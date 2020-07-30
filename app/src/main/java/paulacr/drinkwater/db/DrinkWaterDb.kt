package paulacr.drinkwater.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import paulacr.drinkwater.data.ConsumedWater

@Database(entities = [ConsumedWater::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DrinkWaterDb : RoomDatabase() {

    abstract fun consumedWaterDao(): ConsumedWaterDAO
}
