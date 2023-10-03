package com.example.roomdata.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id:Int? = null,
    @ColumnInfo(name = "name") val title:String,
    var description:String?,
    val createdAt:String,
    var status:String,
    @Ignore var image:String,

    @ColumnInfo(defaultValue = "0")
    var finishedData:String
)

