package com.example.multitableinroom

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multitableinroom.adapters.RegionAdapter
import com.example.multitableinroom.database.AppDatabase
import com.example.multitableinroom.database.entity.Region
import com.example.multitableinroom.database.entity.Student
import com.example.multitableinroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDatabase:AppDatabase

    private lateinit var regionList:ArrayList<Region>
    private lateinit var regionAdapter: RegionAdapter
    //private lateinit var studentList:ArrayList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)

//        appDatabase.regionDao().addRegion(Region(name = "Toshkent"))
//        appDatabase.regionDao().addRegion(Region(name = "Buxoro"))
//        appDatabase.regionDao().addRegion(Region(name = "Fargona"))
//        appDatabase.regionDao().addRegion(Region(name = "Andijon"))
//        appDatabase.regionDao().addRegion(Region(name = "Namangan"))
        regionList  = ArrayList(appDatabase.regionDao().getRegions())
        regionAdapter = RegionAdapter(this,regionList)

        binding.apply {
            spinner.adapter = regionAdapter
            saveBtn.setOnClickListener {
                val name = nameEt.text.toString()
                val age = ageEt.text.toString().toInt()

                //biz tanlaganni alohida objectga olib beradi
                val region = regionList[spinner.selectedItemPosition]

                appDatabase.studentDao().addStudent(
                    Student(
                        name = name,
                        age = age,
                        regionId = region.id
                    )
                )
            }
        }
    }
}