package com.example.roomdatabasess.database.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasess.database.entity.Student
import com.example.roomdatabasess.databinding.ItemStudentBinding

class StudentAdapter(val list:List<Student>):RecyclerView.Adapter<StudentAdapter.Vh>() {
    inner class Vh(var itemStudentBinding: ItemStudentBinding):
        RecyclerView.ViewHolder(itemStudentBinding.root){
        fun onBind(student: Student,position: Int){
        itemStudentBinding.apply {
            nameTv.text = student.name
            ageTv.text = student.age.toString()
            phoneTv.text = student.phoneNumber.toString()

        }
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemStudentBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }
}