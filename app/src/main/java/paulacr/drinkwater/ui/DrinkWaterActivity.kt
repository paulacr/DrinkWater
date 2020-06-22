package paulacr.drinkwater.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import paulacr.drinkwater.ConsumedWaterViewModel
import paulacr.drinkwater.R
import paulacr.drinkwater.databinding.ActivityDrinkWaterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import paulacr.drinkwater.LocalNotificationManager
import paulacr.drinkwater.ui.ViewState.*

class DrinkWaterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityDrinkWaterBinding
    private val viewModel by viewModel<ConsumedWaterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_drink_water)
        binding.viewModel = viewModel
        binding.consumedWaterSelector.onItemSelectedListener = this
        binding.consumedWaterSelector.setSelection(0)

        val notificationManager = LocalNotificationManager(this)
        viewModel.getConsumedWaterLiveData().observe(this, Observer { viewState ->
            var message = ""
            when (viewState) {
                LOADING -> message = getString(R.string.message_loading_data)
                SAVING -> message = getString(R.string.message_saving_data)
                FINISHED -> {
                    message = getString(R.string.message_finished_saving_data)
                    notificationManager.buildNotification()
                }
            }

            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
        })
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        viewModel.consumedWater = parent?.getItemAtPosition(0).toString()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.consumedWater = parent?.getItemAtPosition(position).toString()
    }
}
