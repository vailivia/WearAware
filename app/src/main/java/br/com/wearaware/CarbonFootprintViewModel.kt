package br.com.wearaware

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarbonFootprintViewModel : ViewModel() {
    private val _totalFootprint = MutableStateFlow(0.0)
    val totalFootprint: StateFlow<Double> = _totalFootprint

    fun addFootprint(value: Double) {
        viewModelScope.launch {
            _totalFootprint.value += value
        }
    }
}