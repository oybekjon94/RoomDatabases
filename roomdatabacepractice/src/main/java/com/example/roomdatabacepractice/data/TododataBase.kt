package com.example.roomdatabacepractice.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

//database miz har doim abstrack class bolishi kere
@Database(entities = [Todo::class], version = 3,
    autoMigrations = [AutoMigration(from = 1, to = 2),
    AutoMigration(from = 2, to = 3, spec = TododataBase.Migration2To3::class),
        //yangi malumot qoshilganda
    AutoMigration(from = 3, to = 4)
    ] )

abstract class TododataBase:RoomDatabase() {

    //specni ozimiz create qilishimiz kere
    @RenameColumn(tableName = "todo", fromColumnName = "data", toColumnName = "createdAt")
    class Migration2To3:AutoMigrationSpec

    //bu yerda todoDaoni olishimiz kere
    //bu koprik vazifasini bajaradi yani entity hamda database ortasida malumot
    //almashadi
    abstract fun todoDao(): TodoDao

    //bazamiz qanday yaratiladi
    //companion object bu singletonga yordam beradi
    companion object{
        //faqat bir marta yaralishi kere
        //yani singelton qilish kere va uni prinsipi bor
        //bu esa static
        private var INSTANCE: TododataBase? = null
        fun getInstance(context:Context): TododataBase {
            //agar instance null bolmasa tepadagini yaratsin
            //agarda null bolsa yangi yaratsin
            return INSTANCE ?:
            Room.databaseBuilder(
                context.applicationContext,
                TododataBase::class.java,
                "myRoomDatabase").build()
        }

    }
}