package com.example.multitableinroom.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Region(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name:String
)
