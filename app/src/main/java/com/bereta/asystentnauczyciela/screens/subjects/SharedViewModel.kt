package com.bereta.asystentnauczyciela.screens.subjects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade
import com.bereta.asystentnauczyciela.room.models.GradesAndStudents

class SharedViewModel : ViewModel() {
    private val _selected = MutableLiveData<Subject>()
    val selected: LiveData<Subject> = _selected

    fun select(item: Subject) {
        _selected.value = item
    }
    fun get() = _selected.value

    //grades
    private val _selectedGrades = MutableLiveData<SubjectGrade>()
    val selectedGrades: LiveData<SubjectGrade> = _selectedGrades

    fun selectGrades(item: SubjectGrade) {
        _selectedGrades.value = item
    }
    fun getGrades() = _selectedGrades.value

    //grade
    private val _selectedGrade = MutableLiveData<GradesAndStudents>()
    val selectedGrade: LiveData<GradesAndStudents> = _selectedGrade

    fun selectGrade(item: GradesAndStudents) {
        _selectedGrade.value = item
    }
    fun getGrade() = _selectedGrade.value
}