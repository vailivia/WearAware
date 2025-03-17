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
    fun getAllItems(): Flow<List<CarbonFootprintItem>>

    @Query("SELECT SUM(footprintValue) FROM carbon_footprint")
    suspend fun getTotalFootprint(): Double?

    @Delete
    suspend fun deleteItem(item: CarbonFootprintItem)
}