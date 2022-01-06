package com.bereta.asystentnauczyciela.screens.subjects.grades.list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.StudentWithGradesDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Grade
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradesListViewModel(
    application: Application,
    student: Student,
    subject: Subject
):
    ViewModel() {
    private val studentWithGradesDAO : StudentWithGradesDAO = AssistantDatabase.getInstance(application).studentWithGradesDAO
    var currentStudentGrades: LiveData<List<Grade>> = studentWithGradesDAO.getGrades(subject.ID,student.studentID)
    fun setStudentGrades(idSubject: Long, idstudent: Int) {
        currentStudentGrades = studentWithGradesDAO.getGrades(idSubject,idstudent)
    }
    fun deleteGrade(grade: Grade){
        viewModelScope.launch(Dispatchers.IO){
            studentWithGradesDAO.deleteGrade(grade)

        }
    }

}