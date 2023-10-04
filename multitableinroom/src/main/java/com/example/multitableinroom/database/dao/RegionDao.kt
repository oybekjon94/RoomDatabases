package com.example.multitableinroom.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.multitableinroom.database.entity.Region

@Dao
interface RegionDao {

    @Insert
    fun addRegion(region: Region)

    @Query("select * from region")
    fun getRegions():List<Region>

    @Query("select * from region where id = :id")
    fun getRegionById(id:Int):Region
}