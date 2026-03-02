
// implemented counter app where learned concepts of dataclass,
package com.example.app_demo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.app_demo.databinding.ActivityMainBinding
import kotlin.concurrent.thread
import kotlinx.coroutines.*


data class CounterState(
    val value: Int
)
sealed class useraction{
    object increment:useraction()
    object decrement:useraction()
    object hang:useraction()
}




class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var counter= CounterState(0)
    private fun handleAction(action: useraction) {
        when (action) {
            useraction.increment -> {
                counter = counter.copy(value = counter.value + 1)
                binding.counterView.text = counter.value.toString()
            }

            useraction.decrement -> {
                counter = counter.copy(value = counter.value - 1)
                binding.counterView.text = counter.value.toString()
            }

            useraction.hang -> {
                hangApp()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Restore saved counter value after rotation
        counter = CounterState(savedInstanceState?.getInt("counter") ?: 0)
        binding.counterView.text = counter.value.toString()

        binding.btnIncrement.setOnClickListener {
            handleAction(useraction.increment)
        }
        binding.btnDecrement.setOnClickListener{
            handleAction(useraction.decrement)
        }
        binding.hangit.setOnClickListener {
            handleAction(useraction.hang)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", counter.value)
        Log.d("Lifecycle", "onSaveInstanceState")
    }
    private fun hangApp() {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("coroutine", "Background work started")

            for (i in 1..5000000) {

            }

            Log.d("coroutine", "Background work finished")


        }
    }

}

