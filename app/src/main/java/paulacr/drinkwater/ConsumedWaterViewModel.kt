package paulacr.drinkwater

import androidx.hilt.lifecycle.ViewModelInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import paulacr.drinkwater.notification.NotificationDispatcher
import paulacr.drinkwater.repository.ConsumedWaterDataSource
import paulacr.drinkwater.ui.ViewState

class ConsumedWaterViewModel @ViewModelInject constructor(
    val repository: ConsumedWaterDataSource,
    val notificationDispatcher: NotificationDispatcher
) : BaseViewModel(), CoroutineScope {

    var consumedWater: String? = null

    fun onSaveConsumedWater() {
        postValue(ViewState.SAVING)

        launch {
            consumedWater?.let {
                repository.saveConsumedWater(it.getFormattedNumber().toDouble())
                postValue(ViewState.FINISHED)
            }
        }
    }

    fun getWork() = notificationDispatcher.createWork()

    fun getConsumedWaterLiveData() = viewStateLiveData

    fun scheduleNotification() {
//        notificationDispatcher.startWork()
    }
}
