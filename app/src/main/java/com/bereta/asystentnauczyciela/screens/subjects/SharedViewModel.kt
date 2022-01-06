package com.bereta.asystentnauczyciela.screens.subjects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject

class SharedViewModel : ViewModel() {
    private val _selected = MutableLiveData<Subject>()
    val selected: LiveData<Subject> = _selected

    fun select(item: Subject) {
        _selected.value = item
    }
    fun get() = _selected.value


    //student
    private val _selectedStudent = MutableLiveData<Student>()
    val selectedStudent : LiveData<Student> = _selectedStudent

    fun selectStudent(item: Student) {
        _selectedStudent .value = item
    }
    fun getStudent() = _selectedStudent .value
}