package paulacr.drinkwater

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import paulacr.drinkwater.ui.ViewState

abstract class BaseViewModel(
    val viewStateLiveData: MutableLiveData<ViewState> = MutableLiveData(),
    private val viewModelJob: Job = Job()
) :
    ViewModel(), CoroutineScope {

    open fun postValue(viewState: ViewState) {
        if (viewState == viewStateLiveData.value) return
        viewStateLiveData.postValue(viewState)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}
