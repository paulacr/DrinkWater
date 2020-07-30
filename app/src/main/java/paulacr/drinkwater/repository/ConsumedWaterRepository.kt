package paulacr.drinkwater.repository

import kotlinx.coroutines.Deferred
import paulacr.drinkwater.data.ConsumedWater

interface ConsumedWaterRepository {

    suspend fun saveConsumedWater(amount: Double)

    suspend fun getConsumedWater(): Deferred<List<ConsumedWater>>
}
