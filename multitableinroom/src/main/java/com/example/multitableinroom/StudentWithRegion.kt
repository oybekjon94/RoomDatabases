package com.example.multitableinroom

import androidx.room.Embedded
import androidx.room.Relation
import com.example.multitableinroom.database.entity.Region
import com.example.multitableinroom.database.entity.Student

//model class
//menga ikka jadvalni xam qaytarib ber
data class StudentWithRegion(
    @Embedded //ornatilgan
    val region:Region,
    @Relation(
        parentColumn = "id",
        entityColumn = "region_id"

    )
    val student:Student
)