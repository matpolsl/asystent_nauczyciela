package com.bereta.asystentnauczyciela.screens.students.list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StudentsListViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentsListViewModel::class.java)) {
            return StudentsListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}