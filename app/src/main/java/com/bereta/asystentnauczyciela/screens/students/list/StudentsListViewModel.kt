package com.bereta.asystentnauczyciela.screens.students.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.StudentsDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentsListViewModel(
    application: Application
):
    AndroidViewModel(application) {

    private val studentsDAO : StudentsDAO = AssistantDatabase.getInstance(application).studentsDAO
    val students: LiveData<List<Student>> = studentsDAO.getAll()

    fun deleteStudent(student: Student)
    {
        viewModelScope.launch(Dispatchers.IO) {
            studentsDAO.deleteStudent(student)
        }
    }
}