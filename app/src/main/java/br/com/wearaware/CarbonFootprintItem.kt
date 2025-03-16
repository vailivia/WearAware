package br.com.wearaware

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carbon_footprint")
data class CarbonFootprintItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val description: String,
    val footprintValue: Double
)
