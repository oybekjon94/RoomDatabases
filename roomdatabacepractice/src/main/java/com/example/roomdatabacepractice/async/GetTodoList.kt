package com.example.roomdatabacepractice.async

import android.os.AsyncTask
import android.util.Log
import com.example.roomdatabacepractice.data.Todo
import com.example.roomdatabacepractice.data.TodoDao


class GetTodoList(val todoDao: TodoDao, val getAllTodoCallback: GetAllTodoCallback): AsyncTask<Unit, Unit, List<Todo>>(){
    override fun doInBackground(vararg params: Unit?): List<Todo> {
        return emptyList()
        //return todoDao.getAllTodo()
    }

    override fun onPostExecute(result: List<Todo>?) {
        //natijani korsatish uchun
        super.onPostExecute(result)
        //xammasini olib bolgandan keyin chaqirish kere
        getAllTodoCallback.onAllListGet(result)
        Log.d("TAG", "onPostExecute: ${result?.joinToString()}")
    }

}