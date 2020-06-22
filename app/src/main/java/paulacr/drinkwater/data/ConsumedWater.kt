package paulacr.drinkwater.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "consumed_water")
data class ConsumedWater(val consumedWaterInMl: Double, val dateTime: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
