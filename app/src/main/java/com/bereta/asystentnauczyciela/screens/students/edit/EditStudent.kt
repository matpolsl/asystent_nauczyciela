package com.bereta.asystentnauczyciela.screens.students.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.screens.students.SharedViewModelStudent



class EditStudent : Fragment() {
    private val sharedViewModel: SharedViewModelStudent by activityViewModels<SharedViewModelStudent>()
    lateinit var viewModel: EditStudentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_student, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(requireActivity())[EditStudentViewModel::class.java]
        val textViewFirstName=view.findViewById<TextView>(R.id.textField_first_name)
        val textViewLastName=view.findViewById<TextView>(R.id.textField_last_name)
        val textViewID=view.findViewById<TextView>(R.id.textField_number_id)
        val student = sharedViewModel.get()
        if (student != null) {
            textViewFirstName.text = student.firstName
            textViewLastName.text = student.lastName
            textViewID.text = student.studentID.toString()
            textViewID.isEnabled = false
            (view.findViewById<Button>(R.id.button_update_student)).setOnClickListener{
                val student = Student(student.studentID,textViewFirstName.text.toString(),textViewLastName.text.toString())
                viewModel.updateStudent(student)
                it.findNavController().navigate(R.id.action_studentPage_to_studentsList)
            }
        }

    }
}