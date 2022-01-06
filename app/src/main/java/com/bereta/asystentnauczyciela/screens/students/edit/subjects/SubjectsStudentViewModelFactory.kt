package com.bereta.asystentnauczyciela.screens.students.edit.subjects

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.screens.students.SharedViewModelStudent

class SubjectsStudentViewModelFactory(
    private val application: Application,
    private val student: Student
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubjectsStudentViewModel::class.java)) {
            return SubjectsStudentViewModel(application,student) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}