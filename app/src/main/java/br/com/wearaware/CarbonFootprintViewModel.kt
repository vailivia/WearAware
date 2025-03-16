package br.com.wearaware

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarbonFootprintViewModel : ViewModel() {
    private val _totalFootprint = MutableStateFlow(0.0)
    val totalFootprint: StateFlow<Double> = _totalFootprint

    fun addFootprint(value: Double) {
        _totalFootprint.value += value
    }

    fun subtractFootprint(value: Double) {
        _totalFootprint.value -= value
    }

    fun resetFootprint() {
        _totalFootprint.value = 0.0
    }
}
