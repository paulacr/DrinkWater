package paulacr.drinkwater.di

import androidx.room.Room
import org.koin.dsl.bind
import org.koin.dsl.module
import paulacr.drinkwater.ConsumedWaterViewModel
import paulacr.drinkwater.db.DrinkWaterDb
import paulacr.drinkwater.repository.ConsumedWaterDataSource
import paulacr.drinkwater.repository.ConsumedWaterRepository

val consumedWaterModule = module {
    single { ConsumedWaterViewModel(get()) }
    single { ConsumedWaterRepository(get()) } bind ConsumedWaterDataSource::class
}

val dbModule = module {
    single {
        Room.databaseBuilder(
            get(),
            DrinkWaterDb::class.java,
            "drink-water-database"
        ).build()
    }

    single { get<DrinkWaterDb>().consumedWaterDao() }
}
