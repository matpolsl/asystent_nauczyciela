package com.bereta.asystentnauczyciela.screens.subjects.grades

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.screens.students.edit.subjects.SubjectsStudentViewModel

class GradesSubjectViewModelFactory(
    private val application: Application,
    private val subject: Subject
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GradesSubjectViewModel::class.java)) {
            return GradesSubjectViewModel(application,subject) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}