package paulacr.drinkwater

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import paulacr.drinkwater.di.consumedWaterModule
import paulacr.drinkwater.di.dbModule

class DrinkWaterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        startKoin {
            androidContext(this@DrinkWaterApplication)
            modules(
                listOf(
                    dbModule,
                    consumedWaterModule
                )
            )
        }
    }
}
