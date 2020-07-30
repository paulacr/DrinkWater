package paulacr.drinkwater.repository

import java.time.LocalDateTime
import javax.inject.Inject
import kotlinx.coroutines.Deferred
import paulacr.drinkwater.data.ConsumedWater
import paulacr.drinkwater.db.ConsumedWaterDAO

class ConsumedWaterRepositoryImpl @Inject constructor(val dao: ConsumedWaterDAO) : ConsumedWaterRepository {
    override suspend fun saveConsumedWater(amount: Double) {
        dao.insert(ConsumedWater(amount, LocalDateTime.now().toString()))
    }

    override suspend fun getConsumedWater(): Deferred<List<ConsumedWater>> {
        TODO("Not yet implemented")
    }
}
