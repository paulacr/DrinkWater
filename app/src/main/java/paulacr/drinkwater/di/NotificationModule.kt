package paulacr.drinkwater.di

import android.content.Context
import androidx.hilt.Assisted
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import paulacr.drinkwater.db.ConsumedWaterDAO
import paulacr.drinkwater.db.DrinkWaterDb
import paulacr.drinkwater.notification.NotificationDispatcher
import paulacr.drinkwater.notification.NotificationSchedulerWorker
import paulacr.drinkwater.repository.ConsumedWaterRepository
import paulacr.drinkwater.repository.ConsumedWaterRepositoryImpl

@Module
@InstallIn(value = [ActivityComponent::class, ApplicationComponent::class])
abstract class NotificationModule {

    @Binds
    abstract fun bindConsumedWaterRepository(
        consumedWaterRepositoryImpl: ConsumedWaterRepositoryImpl
    ): ConsumedWaterRepository

    @Provides
    @Singleton
    fun provideConsumedWaterRepository(dao: ConsumedWaterDAO): ConsumedWaterRepositoryImpl {
        return ConsumedWaterRepositoryImpl(dao)
    }

    @Provides
    fun provideNotificationDispatcher(
        @Assisted context: Context,
        @Assisted worker: NotificationSchedulerWorker
    ): NotificationDispatcher {
        return NotificationDispatcher(context, worker)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): DrinkWaterDb {
        return Room.databaseBuilder(
            appContext,
            DrinkWaterDb::class.java,
            "drinkwater.db"
        ).build()
    }
}
