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
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel


class GradesSubjectAdapter(private val grades: LiveData<List<SubjectGrade>>, private val viewModel: GradesSubjectViewModel, private val sharedViewModel: SharedViewModel)
    : RecyclerView.Adapter<GradesSubjectAdapter.GradesSubjectHolder>() {
    inner class GradesSubjectHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textViewName=view.findViewById<TextView>(R.id.name_grades)
        val textViewWeight=view.findViewById<TextView>(R.id.weight)
        val buttondel=view.findViewById<Button>(R.id.button_delete_grades)
        val button=view.findViewById<Button>(R.id.show_grades)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesSubjectHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.row_grades_subject_list,parent,false)
        return GradesSubjectHolder(view)
    }

    override fun onBindViewHolder(holder: GradesSubjectHolder, position: Int) {
        val weight = "Waga: " + grades.value?.get(position)?.weight.toString()
        holder.textViewName.text = grades.value?.get(position)?.name
        holder.textViewWeight.text = weight
        holder.button.setOnClickListener {
            grades.value?.let{ existingGrades->
                sharedViewModel.selectGrades(existingGrades[position])
                it.findNavController().navigate(R.id.action_subject_to_gradesList)
            }
        }
        holder.buttondel.setOnClickListener {
            grades.value?.let{ existingGrades->
                viewModel.rmSubjectGrades(existingGrades.get(position))
            }
        }
    }

    override fun getItemCount()=grades.value?.size?:0
}