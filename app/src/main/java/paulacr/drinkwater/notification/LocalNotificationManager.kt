package paulacr.drinkwater.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import paulacr.drinkwater.R

class LocalNotificationManager(private val context: Context) {

    companion object {
        var CHANNEL_ID = "notification-id"
        var NOTIFICATION = "notification"
    }

    val DEFAULT_DELAY = 10000

    init {
        createNotificationChannel()
    }

    private fun dispatchNotification() =
        NotificationCompat.Builder(context,
            CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_water)
            .setContentTitle("Water reminder")
            .setContentText("How much water are you going to drink NOW?")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

//    private fun scheduleNotification(notification: Notification, delay: Int) {
// //        val notificationIntent = Intent(context, NotificationPublisher::class.java)
//        notificationIntent.putExtra(CHANNEL_ID, 1)
//        notificationIntent.putExtra(NOTIFICATION, notification)
//        val pendingIntent = PendingIntent.getBroadcast(
//            context,
//            0,
//            notificationIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
//        val futureInMillis = SystemClock.elapsedRealtime() + delay
//        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent)
//    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "water-reminder"
            val descriptionText = "some channel description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
