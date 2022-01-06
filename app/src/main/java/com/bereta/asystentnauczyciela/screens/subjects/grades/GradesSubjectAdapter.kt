package com.bereta.asystentnauczyciela.screens.subjects.grades

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel


class GradesSubjectAdapter(private val students: LiveData<List<Student>>, private val viewModel: GradesSubjectViewModel, private val sharedViewModel: SharedViewModel)
    : RecyclerView.Adapter<GradesSubjectAdapter.GradesSubjectHolder>() {
    inner class GradesSubjectHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textViewName=view.findViewById<TextView>(R.id.name_student)
        //val textViewWeight=view.findViewById<TextView>(R.id.weight)
       // val buttondel=view.findViewById<Button>(R.id.button_delete_grades)
        val button=view.findViewById<Button>(R.id.show_grades)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesSubjectHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.row_grades_subject_list,parent,false)
        return GradesSubjectHolder(view)
    }

    override fun onBindViewHolder(holder: GradesSubjectHolder, position: Int) {
        val name = students.value?.get(position)?.firstName.toString() + " " + students.value?.get(position)?.lastName.toString()
        holder.textViewName.text = name
        holder.button.setOnClickListener {
            students.value?.let{ existingStudent->
                sharedViewModel.selectStudent(existingStudent[position])
                it.findNavController().navigate(R.id.action_subject_to_gradesList)
            }
        }
    }

    override fun getItemCount()=students.value?.size?:0
}