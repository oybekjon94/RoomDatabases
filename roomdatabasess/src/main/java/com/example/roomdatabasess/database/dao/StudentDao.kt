package com.example.roomdatabasess.database.dao

import android.provider.UserDictionary.Words
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomdatabasess.database.entity.Student

@Dao
interface StudentDao {

    @Query(value = "select * from talaba")// xamma malumotlarni olish
    fun getAllStudents():List<Student>

    @Insert
    fun addStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Update
    fun editStudent(student: Student)

    //id bilan olish
    @Query("select * from talaba where id = :id")
    fun getStudentById(id:Int):Student

    //like -> ex/bir xil ismli bolgan bir xil xarf
    @Query("select * from talaba where name like '%' || :word || '%'")
    fun getStudentsByName(word:String):List<Student>
}