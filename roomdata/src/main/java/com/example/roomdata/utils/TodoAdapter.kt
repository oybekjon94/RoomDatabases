package com.example.roomdata.utils

import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdata.data.Todo
import com.example.roomdata.databinding.ItemTodoLayoutBinding

private class TodoDiffUtil():DiffUtil.ItemCallback<Todo>(){
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }

}

//class TodoAdapter:ListAdapter<Todo, TodoAdapter.TodoHolder>(TodoDiffUtil()) {
//    inner class TodoHolder(itemTodoLayoutBinding: ItemTodoLayoutBinding):
//            RecyclerView.ViewHolder(itemTodoLayoutBinding.root){
//                fun bind(todo: Todo){
//
//                }
//            }
//}  9:32 todoRoomCorotune recyclerView