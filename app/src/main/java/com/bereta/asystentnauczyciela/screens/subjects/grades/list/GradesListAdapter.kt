package com.bereta.asystentnauczyciela.screens.subjects.grades.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Grade
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel

class GradesListAdapter(
    private val grades: LiveData<List<Grade>>,
    private val viewModel: GradesListViewModel,
    private val sharedViewModel: SharedViewModel,
    private val childFragmentManager: FragmentManager
)
    : RecyclerView.Adapter<GradesListAdapter.GradesListHolder>() {
    inner class GradesListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textViewGrade=view.findViewById<TextView>(R.id.grade)
        val textViewNote=view.findViewById<TextView>(R.id.note)
        val button=view.findViewById<Button>(R.id.button_action_grade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesListHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.row_grades_list,parent,false)
        return GradesListHolder(view)
    }

    override fun onBindViewHolder(holder: GradesListHolder, position: Int) {
        if(grades.value?.get(position)?.grade != null){
            holder.textViewGrade.text = grades.value?.get(position)?.grade.toString()
            if(grades.value?.get(position)?.note != null)
                holder.textViewNote.text = grades.value?.get(position)?.note.toString()
        }

        //holder.textViewWeight.text = weight
        holder.button.setOnClickListener {
            grades.value?.let{ existingGrades->
                viewModel.deleteGrade(existingGrades[position])
            }
        }
    }

    override fun getItemCount()=grades.value?.size?:0
}