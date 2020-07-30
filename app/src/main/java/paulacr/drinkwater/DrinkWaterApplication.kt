package paulacr.drinkwater

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject

@HiltAndroidApp
class DrinkWaterApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    private lateinit var appComponent: ApplicationComponent

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}
