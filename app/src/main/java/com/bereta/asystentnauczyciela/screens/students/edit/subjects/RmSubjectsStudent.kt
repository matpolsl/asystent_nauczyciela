package com.bereta.asystentnauczyciela.screens.students.edit.subjects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.screens.students.SharedViewModelStudent

class RmSubjectsStudent: Fragment() {
    private val sharedViewModel: SharedViewModelStudent by activityViewModels<SharedViewModelStudent>()
    //lateinit var viewModel: EditStudentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_subject, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}