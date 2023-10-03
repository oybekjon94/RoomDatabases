package com.example.roomdatabacepractice.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//bu entity annotation biz implement qilgan room file niki hisoblanadi
//agar entity deb tagida biron nom bilan yozsak usha nom bilan table yaratib beradi
//yokida entity ga qavs ochib nom bersak xam boladi

@Entity(tableName = "todo")
//ttododa birinchi bolib id bolishi kere
data class Todo(
    //id ni nullable qilsak bu ozi autoGenerate qilib yaratadi.id ga hech narsa bermasak
    @PrimaryKey(autoGenerate = true) val id:Int? = null,
    @ColumnInfo val title:String,
    var description:String?,
    val createdAdd:String,
    var status:String,
    @ColumnInfo(defaultValue = "0")
    var finishedData:String

    )
