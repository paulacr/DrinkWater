package paulacr.drinkwater.notification

import android.content.Context
import androidx.hilt.Assisted
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import javax.inject.Inject

class NotificationDispatcher @Inject constructor(
    @Assisted val context: Context,
    @Assisted val worker: NotificationSchedulerWorker
) {

    internal fun createWork(): PeriodicWorkRequest {
        val work = worker.createWorkRequest(Data.EMPTY)
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork("Smart work", ExistingPeriodicWorkPolicy.REPLACE, work)
        return work
    }
}
