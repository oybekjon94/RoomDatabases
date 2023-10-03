package com.example.roomdatabasess.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdatabasess.database.dao.StudentDao
import com.example.roomdatabasess.database.entity.Student

@Database(entities = [Student::class], version = 2)
abstract class AppDatabase:RoomDatabase() {

    abstract fun studentDao():StudentDao
    //endi databaseni yaratishni koramiz
    //com.obj-> bu static korinishdagi ozgaruvchilarni elon qilishda
    //yordam beradi
    companion object{
        private var INSTANSE:AppDatabase? = null

        //kop oqimlarni ketma ketlikda qabul qiladi
        @Synchronized
        fun getInstance(context: Context):AppDatabase{
            if (INSTANSE == null){
                INSTANSE = Room.databaseBuilder(context,AppDatabase::class.java,"my_db")
                    .allowMainThreadQueries() //main threadda querylarga ruhsat
                    .addMigrations(MIGRATION_1_2)
                    .build()
            }
            return INSTANSE!!
        }


        // Migration from 1 to 2, Room 2.1.0.
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE talaba ADD COLUMN email TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}