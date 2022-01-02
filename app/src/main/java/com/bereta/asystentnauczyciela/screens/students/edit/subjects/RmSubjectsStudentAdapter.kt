package com.bereta.asystentnauczyciela.screens.students.edit.subjects

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Subject

class RmSubjectsStudentAdapter(private val subject: LiveData<List<Subject>>, private val viewModel: SubjectsStudentViewModel)
    : RecyclerView.Adapter<RmSubjectsStudentAdapter.RmSubjectsStudentHolder>() {
    inner class RmSubjectsStudentHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textViewName=view.findViewById<TextView>(R.id.name)
        val textViewDate=view.findViewById<TextView>(R.id.date)
        val button=view.findViewById<Button>(R.id.button_subject_row_student)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RmSubjectsStudentHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.row_student_subjects,parent,false)
        return RmSubjectsStudentHolder(view)
    }

    override fun onBindViewHolder(holder: RmSubjectsStudentHolder, position: Int) {
        val date = subject.value?.get(position)?.dayOfWeek + " " + subject.value?.get(position)?.timeFrom + "-" + subject.value?.get(position)?.timeTo
        holder.textViewName.text=subject.value?.get(position)?.name
        holder.textViewDate.text=date
        val img: Drawable? = holder.button.context.getDrawable(R.drawable.ic_baseline_horizontal_rule_24)
        holder.button.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null)
        holder.button.setOnClickListener {
            subject.value?.let{ existingSubject->
                viewModel.rmSubject(existingSubject.get(position))
            }
        }
    }

    override fun getItemCount()=subject.value?.size?:0

}