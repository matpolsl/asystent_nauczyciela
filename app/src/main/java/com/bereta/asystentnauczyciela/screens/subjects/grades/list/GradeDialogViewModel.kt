package com.bereta.asystentnauczyciela.screens.subjects.grades.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.StudentWithGradesDAO
import com.bereta.asystentnauczyciela.room.DAO.SubjectWithGradesDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.StudentGrade
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradeDialogViewModel(application: Application): AndroidViewModel(application)
{
    private val studentWithGradesDAO : StudentWithGradesDAO = AssistantDatabase.getInstance(application).studentWithGradesDAO

    fun addGrade(grade: StudentGrade){
        viewModelScope.launch(Dispatchers.IO){
            studentWithGradesDAO.insertGrade(grade)
            //Toast.makeText(getApplication(),"Dodano nową grupę ocen", Toast.LENGTH_LONG).show()
        }
    }
    fun updateGrade(grade: StudentGrade){
    viewModelScope.launch(Dispatchers.IO){
        studentWithGradesDAO.updateGrade(grade)
        //Toast.makeText(getApplication(),"Dodano nową grupę ocen", Toast.LENGTH_LONG).show()
    }
}
}