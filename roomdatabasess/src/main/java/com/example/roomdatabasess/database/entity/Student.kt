package com.example.roomdatabasess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//database ustunlari
@Entity(tableName = "talaba") //boshqa nom berishni xoxlasak
data class Student(
    @PrimaryKey(autoGenerate = true) //id ni automatik ozi create qiladi
    val id:Int=0,
    val name:String,
    val age:Int,
    @ColumnInfo(name = "phone_number")
    val phoneNumber:String,  // phone_number //shunaqa nom berish kere
    val email:String
)
