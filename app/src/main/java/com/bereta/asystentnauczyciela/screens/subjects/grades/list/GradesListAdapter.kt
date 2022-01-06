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
import com.bereta.asystentnauczyciela.room.models.GradesAndStudents
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel

class GradesListAdapter(
    private val grades: LiveData<List<GradesAndStudents>>,
    private val viewModel: GradesListViewModel,
    private val sharedViewModel: SharedViewModel,
    private val childFragmentManager: FragmentManager
)
    : RecyclerView.Adapter<GradesListAdapter.GradesListHolder>() {
    inner class GradesListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textViewName=view.findViewById<TextView>(R.id.name_student)
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
        val nameStudent = grades.value?.get(position)?.student?.firstName.toString() + " " + grades.value?.get(position)?.student?.lastName.toString()
        holder.textViewName.text = nameStudent
        if(grades.value?.get(position)?.grade != null){
            holder.textViewGrade.text = grades.value?.get(position)?.grade.toString()
            if(grades.value?.get(position)?.note != null)
                holder.textViewNote.text = grades.value?.get(position)?.note.toString()
        }

        //holder.textViewWeight.text = weight
        holder.button.setOnClickListener {
            grades.value?.let{ existingGrades->
                sharedViewModel.selectGrade(existingGrades[position])
                Log.d("Test2",existingGrades[position].student.toString())
                //it.findNavController().navigate(R.id.action_subject_to_gradesList)
                val createDialog = GradeDialog()
                createDialog.show(childFragmentManager, "NoticeDialogFragment")
            }
        }
    }

    override fun getItemCount()=grades.value?.size?:0
}