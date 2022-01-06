package com.bereta.asystentnauczyciela.screens.subjects.grades.list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject


class GradesListViewModelFactory(
    private val application: Application,
    private val subject: Subject,
    private val student: Student,
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GradesListViewModel::class.java)) {
            return GradesListViewModel(application,student,subject) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}