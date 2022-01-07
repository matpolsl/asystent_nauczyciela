package com.bereta.asystentnauczyciela.screens.subjects.grades.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.StudentWithGradesDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Grade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradeDialogViewModel(application: Application): AndroidViewModel(application)
{
    private val studentWithGradesDAO : StudentWithGradesDAO = AssistantDatabase.getInstance(application).studentWithGradesDAO

    fun addGrade(grade: Grade){
        viewModelScope.launch(Dispatchers.IO){
            studentWithGradesDAO.inseretGrade(grade)
            //Toast.makeText(getApplication(),"Dodano nową grupę ocen", Toast.LENGTH_LONG).show()
        }
    }
}
