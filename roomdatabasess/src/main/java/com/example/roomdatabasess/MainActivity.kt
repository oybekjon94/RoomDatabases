package com.example.roomdatabasess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdatabasess.database.AppDatabase
import com.example.roomdatabasess.database.adapter.StudentAdapter
import com.example.roomdatabasess.database.entity.Student
import com.example.roomdatabasess.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val appDatabase: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var list: ArrayList<Student>
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            list = ArrayList(appDatabase.studentDao().getAllStudents())
            studentAdapter = StudentAdapter(list)
            rv.adapter = studentAdapter

            saveBtn.setOnClickListener {
                val student = Student(
                    name = nameEt.text.toString(),
                    age = ageEt.text.toString().toInt(),
                    phoneNumber = phoneEt.text.toString(),
                    email = "@gmail.com"
                )
                appDatabase.studentDao().addStudent(student)
                list.add(student)
                studentAdapter.notifyItemChanged(list.size)
            }
            }
    }
}