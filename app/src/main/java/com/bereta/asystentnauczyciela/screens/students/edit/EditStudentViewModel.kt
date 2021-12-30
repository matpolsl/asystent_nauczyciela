package com.bereta.asystentnauczyciela.screens.students.edit

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.StudentsDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditStudentViewModel(application: Application): AndroidViewModel(application)
{
    private val studentsDAO : StudentsDAO

    init{
        studentsDAO= AssistantDatabase.getInstance(application).studentsDAO
    }
    fun updateStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO){
            studentsDAO.updateStudent(student)
            //Toast.makeText(getApplication(),"Zmiany zostały zapisane",Toast.LENGTH_LONG).show()
        }
    }
}