package paulacr.drinkwater

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import paulacr.drinkwater.ui.ViewState
import paulacr.drinkwater.repository.ConsumedWaterRepository
import kotlin.coroutines.CoroutineContext

class ConsumedWaterViewModel(private val repository: ConsumedWaterRepository) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    private val viewModelJob = Job()

    private val consumedWaterLiveData = MutableLiveData<ViewState>()

    var consumedWater: String? = null

    fun onSaveConsumedWater() {
        consumedWaterLiveData.postValue(ViewState.SAVING)

            launch {
            consumedWater?.let {
                repository.saveConsumedWater(it.getFormattedNumber().toDouble())
                consumedWaterLiveData.postValue(ViewState.FINISHED)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getConsumedWaterLiveData() = consumedWaterLiveData
}
