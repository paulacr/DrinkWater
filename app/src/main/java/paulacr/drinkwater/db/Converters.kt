package paulacr.drinkwater.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import paulacr.drinkwater.data.ConsumedWater

class Converters {

    val gson = Gson()

    @TypeConverter
    fun fromJson(consumedWater: String): List<ConsumedWater> {
        val type = object : TypeToken<List<ConsumedWater>>() {}.type
        return gson.fromJson(consumedWater, type)
    }

    @TypeConverter
    fun toJson(list: List<ConsumedWater>): String? {
        return gson.toJson(list)
    }
}

private fun <T : Enum<T>> T.toInt(): Int = this.ordinal

private inline fun <reified T : Enum<T>> Int.toEnum(): T = enumValues<T>()[this]
