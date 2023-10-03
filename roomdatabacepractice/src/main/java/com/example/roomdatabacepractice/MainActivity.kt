package com.example.roomdatabacepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.roomdatabacepractice.data.Todo
import com.example.roomdatabacepractice.data.TododataBase
import com.example.roomdatabacepractice.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private lateinit var database: TododataBase
    private lateinit var binding: ActivityMainBinding
    private val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = TododataBase.getInstance(this)
        val insertTodo = Todo(null,"Asynctodo","","","New","20:09:2022")

        //agarda bizda globalScopedan  foydalanib qilsak
        //appdan chiqib ketsak xam global scope ishlab turadi xamda memory leakga olib keladi
        //shuning uchun main threadda lifecycle scopedan foydalanishimiz kere

        lifecycleScope.launchWhenCreated {
            database.todoDao().insert(insertTodo)
        }
        //flowni oqib olish uchun
        lifecycleScope.launchWhenCreated {
            database.todoDao().getAllTodo().collectLatest {
                Log.d(TAG, "onCreate: $it")
            }
        }

    }

}