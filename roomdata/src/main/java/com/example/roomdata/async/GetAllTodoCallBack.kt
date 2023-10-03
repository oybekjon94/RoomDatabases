package com.example.roomdata.async

import com.example.roomdata.data.Todo

interface GetAllTodoCallBack {
    fun onAllListGet(list: List<Todo>?)
}