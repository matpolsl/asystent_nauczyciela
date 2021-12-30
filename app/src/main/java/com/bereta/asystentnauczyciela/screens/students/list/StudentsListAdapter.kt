package com.bereta.asystentnauczyciela.screens.students.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.screens.students.SharedViewModelStudent


class StudentsListAdapter(private val student: LiveData<List<Student>>,private  val sharedViewModel: SharedViewModelStudent, private val viewModel: StudentsListViewModel)
    : RecyclerView.Adapter<StudentsListAdapter.StudentsListHolder>() {


    inner class StudentsListHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewName = view.findViewById<TextView>(R.id.name_student)
        val buttonShow = view.findViewById<Button>(R.id.button_show_student)
        val buttonDel = view.findViewById<Button>(R.id.button_delete_student)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsListHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_students_list, parent, false)
        return StudentsListHolder(view)
    }


    override fun onBindViewHolder(holder: StudentsListHolder, position: Int) {

        holder.textViewName.text =
            student.value?.get(position)?.firstName + " " + student.value?.get(position)?.lastName


        holder.buttonDel.setOnClickListener {

            student.value?.let { existingStudent ->
                viewModel.deleteStudent(existingStudent[position])
            }

        }
        holder.buttonShow.setOnClickListener {
            student.value?.let { existingStudent ->
                sharedViewModel.select(existingStudent.get(position))
                it.findNavController().navigate(R.id.action_studentsList_to_studentPage)
            }

        }
    }

    override fun getItemCount() = student.value?.size ?: 0
}
