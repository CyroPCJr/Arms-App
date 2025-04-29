package com.example.armsapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.armsapp.data.local.dao.ArmsTeamDao
import com.example.armsapp.data.local.dao.ProjectDao
import com.example.armsapp.data.local.entities.ArmsTeamEntity
import com.example.armsapp.data.local.entities.ProjectEntity

@Database(
    entities = [ProjectEntity::class, ArmsTeamEntity::class],
    version = 1,
)
abstract class ArmsDatabase : RoomDatabase() {

    abstract fun projectDao(): ProjectDao
    abstract fun armsTeamDao(): ArmsTeamDao

    companion object {
        @Volatile
        private var Instance: ArmsDatabase? = null

        fun getDatabase(context: Context): ArmsDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ArmsDatabase::class.java,
                    "arms_db"
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }

}