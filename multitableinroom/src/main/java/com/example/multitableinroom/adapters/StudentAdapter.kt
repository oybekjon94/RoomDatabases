package com.example.multitableinroom.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.multitableinroom.StudentWithRegion
import com.example.multitableinroom.databinding.ItemRegionBinding
import com.example.multitableinroom.databinding.ItemStudentBinding

class StudentAdapter(val list: List<StudentWithRegion>):RecyclerView.Adapter<StudentAdapter.Vh>() {

    inner class Vh(val itemStudentBinding: ItemStudentBinding):
            RecyclerView.ViewHolder(itemStudentBinding.root){
                fun onBind(studentWithRegion: StudentWithRegion){
                    itemStudentBinding.apply {
                        nameTv.text = studentWithRegion.student.name
                        ageTv.text = studentWithRegion.student.age.toString()
                        regionTv.text = studentWithRegion.student.name

                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemStudentBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])

    }
}