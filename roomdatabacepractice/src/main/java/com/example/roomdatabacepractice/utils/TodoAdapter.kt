package com.example.roomdatabacepractice.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabacepractice.data.Todo
import com.example.roomdatabacepractice.databinding.ItemLayoutBinding

private class TodoDiffUtil():DiffUtil.ItemCallback<Todo>(){
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }

}

class TodoAdapter:ListAdapter<Todo,TodoAdapter.TodoHolder>(TodoDiffUtil()) {


    inner class TodoHolder(private val itemLayoutBinding: ItemLayoutBinding):
            RecyclerView.ViewHolder(itemLayoutBinding.root){
                fun bind(todo:Todo){
                    itemLayoutBinding.textView.text = todo.title
                    itemLayoutBinding.textView2.text = todo.description
                    itemLayoutBinding.checkBox.text = todo.createdAdd
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        return TodoHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(getItem(position))
    }
}