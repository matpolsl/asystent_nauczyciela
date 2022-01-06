package com.bereta.asystentnauczyciela.screens.subjects.grades.list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.StudentWithGradesDAO
import com.bereta.asystentnauczyciela.room.DAO.SubjectWithGradesDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade
import com.bereta.asystentnauczyciela.room.models.GradesAndStudents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradesListViewModel(
    application: Application,
    gradesCategory: SubjectGrade
):
    ViewModel() {
    private val studentWithGradesDAO : StudentWithGradesDAO = AssistantDatabase.getInstance(application).studentWithGradesDAO
    var currentStudentGrades: LiveData<List<GradesAndStudents>> = studentWithGradesDAO.getGradesStudents(gradesCategory.subjectID,gradesCategory.ID)
    fun setStudentGrades(idSubject: Long, idGrades: Long) {
        currentStudentGrades = studentWithGradesDAO.getGradesStudents(idSubject,idGrades)
    }

    fun rmSubjectGrades(grade: SubjectGrade){
        viewModelScope.launch(Dispatchers.IO){
            //subjectWithGradesDAO.deleteGradeSubject(grade)
        }
    }

}