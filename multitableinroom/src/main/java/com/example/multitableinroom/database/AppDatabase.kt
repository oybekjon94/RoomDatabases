package com.example.multitableinroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.multitableinroom.database.dao.RegionDao
import com.example.multitableinroom.database.dao.StudentDao
import com.example.multitableinroom.database.entity.Region
import com.example.multitableinroom.database.entity.Student

@Database(entities = [Student::class,Region::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun studentDao():StudentDao
    abstract fun regionDao():RegionDao



    companion object {
        private var appDatabase:AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context):AppDatabase{
            if (appDatabase == null){
                appDatabase = Room.databaseBuilder(context,AppDatabase::class.java,"my_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return appDatabase!!
        }
    }
}