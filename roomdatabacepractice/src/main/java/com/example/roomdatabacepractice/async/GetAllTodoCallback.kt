package com.example.roomdatabacepractice.async

import com.example.roomdatabacepractice.data.Todo

//olgan malumotni yana mainclassga qaytarib berish uchun yana CallBack qilinadi
interface GetAllTodoCallback {
    //list qaytarib berishi uchun paramga list berish kere
    fun onAllListGet(list: List<Todo>?)

}