package br.com.wearaware.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.wearaware.AppDatabase
import br.com.wearaware.CarbonFootprintItem
import br.com.wearaware.CarbonFootprintRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CarbonFootprintViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CarbonFootprintRepository
    val allItems: Flow<List<CarbonFootprintItem>>

    init {
        val dao = AppDatabase.getDatabase(application).carbonFootprintDao()
        repository = CarbonFootprintRepository(dao)
        allItems = repository.allItems
    }

    val totalFootprint: StateFlow<Double> = allItems
        .map { items -> items.sumOf { it.footprintValue } }
        .stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    val totalItems: StateFlow<Int> = allItems
        .map { items -> items.sumOf { it.quantity } }  // Soma a quantidade de cada item
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    fun addItem(item: CarbonFootprintItem) = viewModelScope.launch {
        repository.insertItem(item)
    }

    fun deleteItem(item: CarbonFootprintItem) = viewModelScope.launch {
        repository.deleteItem(item)
    }
}
