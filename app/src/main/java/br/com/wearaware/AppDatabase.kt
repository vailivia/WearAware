package br.com.wearaware

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CarbonFootprintItem::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carbonFootprintDao(): CarbonFootprintDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "carbon_footprint_db"
                )
                    .fallbackToDestructiveMigration() // recria o banco ao mudar versão
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

