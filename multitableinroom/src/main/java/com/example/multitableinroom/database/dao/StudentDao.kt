package com.example.multitableinroom.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.multitableinroom.StudentWithRegion
import com.example.multitableinroom.database.entity.Student

@Dao
interface StudentDao {

    @Insert
    fun addStudent(student: Student)

    @Query("select * from student")
    fun getStudents():Student

    @Query("select * from student where id = :id")
    fun getStudentById(id:Int):Student

    @Transaction
    @Query("select * from student, region where student.region_id = region_id")
    fun getStudentsWithRegion():List<StudentWithRegion>
}