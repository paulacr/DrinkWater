package paulacr.drinkwater.notification

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class NotificationSchedulerWorker @WorkerInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParameters: WorkerParameters
) :
    Worker(appContext, workerParameters) {

    override fun doWork(): Result {
        Log.i("Work manager", "sending notification")
        return Result.success()
    }

    private fun createConstraints() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresBatteryNotLow(true)
        .setRequiresStorageNotLow(true)
        .build()

    internal fun createWorkRequest(data: Data) =
        PeriodicWorkRequestBuilder<NotificationSchedulerWorker>(1, TimeUnit.MINUTES)
            // set input data for the work
            .setInputData(data)
            .setConstraints(createConstraints())
            // setting a backoff on case the work needs to retry
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()
}
