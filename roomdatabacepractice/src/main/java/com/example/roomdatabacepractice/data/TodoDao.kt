package com.example.roomdatabacepractice.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(todo: Todo)

    //todoni listini  insert qilmoqchi bolsak
    @Insert
    suspend fun insertAll(todoList:List<Todo>)

    //delete qilish uchun
    @Delete
    suspend fun delete(todo: Todo)

    //update
    @Update
    suspend fun update(todo: Todo)

    //flow bu bizning data bazamizda biror bir ozgarish bolsa shu ozgarishni
    //activityga yetkazishda foy.
    @Query("select * from todo limit 10")
    fun getAllTodo():Flow<List<Todo>>

    @Query("select * from todo where id = :id")
    suspend fun getTodoById(id:Int): Todo
}