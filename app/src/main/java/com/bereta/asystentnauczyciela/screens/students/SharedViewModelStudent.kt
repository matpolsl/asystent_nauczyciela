package com.bereta.asystentnauczyciela.screens.students

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bereta.asystentnauczyciela.room.entities.Student

class SharedViewModelStudent: ViewModel() {
    private val _selected = MutableLiveData<Student>()
    val selected: LiveData<Student> = _selected

    fun select(item: Student) {
        _selected.value = item
    }
    fun get() = _selected.value

}