package com.example.roomdata

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.roomdata.data.Todo
import com.example.roomdata.data.TodoDatabase
import com.example.roomdata.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var database: TodoDatabase

    private lateinit var binding: ActivityMainBinding
    private val TAG = "TAG"
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = TodoDatabase.getInstance(this)


        lifecycleScope.launchWhenCreated {

            database.todoDao().getAllTodo().collectLatest {
                counter = it.size
                Log.d(TAG,"onCreate: $it")
            }
        }

        binding.fabBtn.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                val insertTodo = Todo(null,"Coroutine Todo ${counter+1}","","","","New","20:09:2023")

                database.todoDao().insert(insertTodo)
            }
        }

    }

}