package com.bereta.asystentnauczyciela.screens.students.add

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.StudentsDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddStudentViewModel(application: Application): AndroidViewModel(application)
{
    private val studentsDAO : StudentsDAO

    init{
        studentsDAO= AssistantDatabase.getInstance(application).studentsDAO
    }
    fun addStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO){
            studentsDAO.insertStudent(student)
        }
        val fName = student.firstName
        val lName = student.lastName
        Toast.makeText(getApplication(),"Student $fName $lName", Toast.LENGTH_LONG).show()
    }
}