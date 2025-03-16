package br.com.wearaware

import kotlinx.coroutines.flow.Flow

class CarbonFootprintRepository(private val dao: CarbonFootprintDao) {
    val allItems: Flow<List<CarbonFootprintItem>> = dao.getAllItems()

    suspend fun insertItem(item: CarbonFootprintItem) {
        dao.insertItem(item)
    }

    suspend fun deleteItem(item: CarbonFootprintItem) {
        dao.deleteItem(item)
    }
}