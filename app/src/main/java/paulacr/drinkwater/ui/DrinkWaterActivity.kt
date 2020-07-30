package paulacr.drinkwater.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import paulacr.drinkwater.ConsumedWaterViewModel
import paulacr.drinkwater.R
import paulacr.drinkwater.databinding.ActivityDrinkWaterBinding
import paulacr.drinkwater.notification.LocalNotificationManager

@AndroidEntryPoint
class DrinkWaterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    @Inject lateinit var viewModel: ConsumedWaterViewModel

    private lateinit var binding: ActivityDrinkWaterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_drink_water)
        binding.viewModel = viewModel
        binding.consumedWaterSelector.onItemSelectedListener = this
        binding.consumedWaterSelector.setSelection(0)
    }

    override fun onResume() {
        super.onResume()
        setupObserver(viewModel.getWork())

        viewModel.scheduleNotification()

        val notificationManager =
            LocalNotificationManager(this)
        val observer = Observer<ViewState> {
            var message = ""
            when (it) {
                ViewState.LOADING -> {
                    message = getString(R.string.message_loading_data)
                }
                ViewState.SAVING -> {
                    message = getString(R.string.message_saving_data)
                }
                ViewState.FINISHED -> {
                    message = getString(R.string.message_finished_saving_data)
//                    notificationManager.scheduleNotification(this, 10000, 0)
                }
            }
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
        }
        viewModel.getConsumedWaterLiveData().observe(this, observer)
    }

    private fun setupObserver(work: PeriodicWorkRequest) {
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(work.id)
            .observe(this, Observer { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    // FINISHED SUCCESSFULLY!
                }
            })
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        viewModel.consumedWater = parent?.getItemAtPosition(0).toString()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.consumedWater = parent?.getItemAtPosition(position).toString()
    }
}
