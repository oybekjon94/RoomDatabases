package com.example.multitableinroom.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//boshqa tableni boglaymiz
@Entity(
    foreignKeys = [ForeignKey(
        entity = Region::class,
        parentColumns = arrayOf("id"), //bu Studentin id si!
        childColumns = arrayOf("region_id"), //bu id Regionni id si!
        onDelete = ForeignKey.CASCADE
    )]
)
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: Int,
    @ColumnInfo(name = "region_id")
    val regionId: Int,
)