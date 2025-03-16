package br.com.wearaware

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarbonFootprintDao {
    @Insert
    suspend fun insertItem(item: CarbonFootprintItem)

    @Query("SELECT * FROM carbon_footprint")
    suspend fun getAllItems(): List<CarbonFootprintItem>

    @Query("SELECT SUM(footprintValue) FROM carbon_footprint")
    suspend fun getTotalFootprint(): Double?
}
