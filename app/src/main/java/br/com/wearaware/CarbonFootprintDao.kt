package br.com.wearaware

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface CarbonFootprintDao {
    @Insert
    suspend fun insertItem(item: CarbonFootprintItem)

    @Query("SELECT * FROM carbon_footprint")
    fun getAllItems(): Flow<List<CarbonFootprintItem>>  // Retorna Flow

    @Query("SELECT SUM(footprintValue) FROM carbon_footprint")
    suspend fun getTotalFootprint(): Double?

    @Delete // Adicionando o método deleteItem (ou delete, se for o caso)
    suspend fun deleteItem(item: CarbonFootprintItem)
}