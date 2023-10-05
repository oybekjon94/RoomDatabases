package com.example.multitableinroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multitableinroom.adapters.RegionAdapter
import com.example.multitableinroom.adapters.StudentAdapter
import com.example.multitableinroom.database.AppDatabase
import com.example.multitableinroom.database.entity.Region
import com.example.multitableinroom.database.entity.Student
import com.example.multitableinroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDatabase:AppDatabase

    private lateinit var regionList:ArrayList<Region>
    private lateinit var regionAdapter: RegionAdapter
    private lateinit var studentList:ArrayList<StudentWithRegion>
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)

        appDatabase.regionDao().addRegion(Region(name = "Toshkent"))
        appDatabase.regionDao().addRegion(Region(name = "Buxoro"))
        appDatabase.regionDao().addRegion(Region(name = "Fargona"))
        appDatabase.regionDao().addRegion(Region(name = "Andijon"))
        appDatabase.regionDao().addRegion(Region(name = "Namangan"))
        regionList  = ArrayList(appDatabase.regionDao().getRegions())
        regionAdapter = RegionAdapter(this,regionList)

        studentList = ArrayList(appDatabase.studentDao().getStudentsWithRegion())
        studentAdapter = StudentAdapter(studentList)
        binding.apply {
            spinner.adapter = regionAdapter
            rv.adapter = studentAdapter
            saveBtn.setOnClickListener {
                val name = nameEt.text.toString()
                val age = ageEt.text.toString().toInt()

                //biz tanlaganni alohida objectga olib beradi
                val region = regionList[spinner.selectedItemPosition]
                val student = Student(
                    name = name,
                    age = age,
                    regionId = region.id
                )
                appDatabase.studentDao().addStudent(student)
                studentList.add(StudentWithRegion(region,student))
                studentAdapter.notifyItemInserted(studentList.size)
            }
        }
    }
}
//1:12