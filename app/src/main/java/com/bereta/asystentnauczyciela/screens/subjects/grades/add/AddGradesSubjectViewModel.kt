package com.bereta.asystentnauczyciela.screens.subjects.grades.add

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.SubjectWithGradesDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddGradesSubjectViewModel(application: Application): AndroidViewModel(application)
{
    private val subjectWithGradesDAO : SubjectWithGradesDAO

    init{
        subjectWithGradesDAO = AssistantDatabase.getInstance(application).subjectWithGradesDAO
    }
    fun addSubjectGrades(grade: SubjectGrade){
        viewModelScope.launch(Dispatchers.IO){
            subjectWithGradesDAO.insertGradeSubject(grade)
            //Toast.makeText(getApplication(),"Dodano nową grupę ocen", Toast.LENGTH_LONG).show()
        }
    }
}