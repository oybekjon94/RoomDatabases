package com.example.roomdatabacepractice.async

import android.os.AsyncTask
import com.example.roomdatabacepractice.data.Todo
import com.example.roomdatabacepractice.data.TodoDao

//AsyncTaskni alohida classga olganimizda bindingni topa olmaydi
//Listenerni berishimiz kere
class AddtodoAsyncTask(val todoDao: TodoDao, val addTodoCallBack: AddTodoCallBack): AsyncTask<Todo, Unit, Unit>(){

    //birinchi bolib onPreExucute keladi
    override fun onPreExecute() {
        super.onPreExecute()
        addTodoCallBack.onControlProgressBar()

    }
    override fun doInBackground(vararg todo: Todo?) {
        Thread.sleep(2000)
        todo[0]?.apply {
            //data qushish uchun
           // todoDao.insert(this)
        }
    }
    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        addTodoCallBack.onFinish()
    }
}