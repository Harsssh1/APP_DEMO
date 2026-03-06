package com.example.app_demo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class CounterState(
    val value: Int
)

sealed class UserAction {
    object Increment : UserAction()
    object Decrement : UserAction()
    object Hang : UserAction()
}

class counterviewmodel : ViewModel() {

    private val _counter = MutableLiveData(CounterState(0))
    val counter: LiveData<CounterState> = _counter

    fun handleAction(action: UserAction) {
        when (action) {

            UserAction.Increment -> {
                val current = _counter.value ?: CounterState(0)
                _counter.value = current.copy(value = current.value + 1)
            }

            UserAction.Decrement -> {
                val current = _counter.value ?: CounterState(0)
                _counter.value = current.copy(value = current.value - 1)
            }

            UserAction.Hang -> {
                hangApp()
            }
        }
    }

    private fun hangApp() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("coroutine", "Background work started")

            for (i in 1..5_000_000) {
            }

            Log.d("coroutine", "Background work finished")
        }
    }
}