package com.bereta.asystentnauczyciela.screens.students.edit.subjects

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.screens.students.SharedViewModelStudent
import com.bereta.asystentnauczyciela.screens.students.list.StudentsListAdapter
import com.bereta.asystentnauczyciela.screens.students.list.StudentsListViewModel

class AddSubjectsStudentAdapter(private val subject: LiveData<List<Subject>>, private val viewModel: SubjectsStudentViewModel)
    : RecyclerView.Adapter<AddSubjectsStudentAdapter.AddSubjectsStudentHolder>() {
    inner class AddSubjectsStudentHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textViewName=view.findViewById<TextView>(R.id.name)
        val textViewDate=view.findViewById<TextView>(R.id.date)
        val button=view.findViewById<Button>(R.id.button_subject_row_student)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddSubjectsStudentHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.row_student_subjects,parent,false)
        return AddSubjectsStudentHolder(view)
    }

    override fun onBindViewHolder(holder: AddSubjectsStudentHolder, position: Int) {
        val date = subject.value?.get(position)?.dayOfWeek + " " + subject.value?.get(position)?.timeFrom + "-" + subject.value?.get(position)?.timeTo
        holder.textViewName.text=subject.value?.get(position)?.name
        holder.textViewDate.text=date

        holder.button.setOnClickListener {
            Log.d("mvm","Test")
            subject.value?.let{ existingSubject->
                viewModel.addSubject(existingSubject.get(position))
            }
        }
    }

    override fun getItemCount()=subject.value?.size?:0

}