package com.example.roomdata.async

import android.os.AsyncTask
import android.util.Log
import com.example.roomdata.data.Todo
import com.example.roomdata.data.TodoDao

class GetTodoList(val todoDao: TodoDao, val getAllTodoCallBack: GetAllTodoCallBack): AsyncTask<Unit, Unit, List<Todo>>(){
    override fun doInBackground(vararg params: Unit?): List<Todo> {
        return emptyList()
//        return todoDao.getAllTodo()
    }

    override fun onPostExecute(result: List<Todo>?) {
        super.onPostExecute(result)
        getAllTodoCallBack.onAllListGet(result)
        Log.d("TAG","onPostExecute: ${result?.joinToString()}")
    }
}