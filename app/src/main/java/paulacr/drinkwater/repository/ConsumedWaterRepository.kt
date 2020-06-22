package paulacr.drinkwater.repository

import java.time.LocalDateTime
import kotlinx.coroutines.Deferred
import paulacr.drinkwater.data.ConsumedWater
import paulacr.drinkwater.db.ConsumedWaterDAO

class ConsumedWaterRepository(private val dao: ConsumedWaterDAO) : ConsumedWaterDataSource {
    override suspend fun saveConsumedWater(amount: Double) {
        dao.insert(ConsumedWater(amount, LocalDateTime.now().toString()))
    }

    override suspend fun getConsumedWater(): Deferred<List<ConsumedWater>> {
        TODO("Not yet implemented")
    }
}
