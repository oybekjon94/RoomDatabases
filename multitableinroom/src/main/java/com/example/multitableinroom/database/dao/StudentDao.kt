package com.example.multitableinroom.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.multitableinroom.database.entity.Student

@Dao
interface StudentDao {

    @Insert
    fun addStudent(student: Student)

    @Query("select * from student")
    fun getStudents():Student

    @Query("select * from student where id = :id")
    fun getStudentById(id:Int):Student
}