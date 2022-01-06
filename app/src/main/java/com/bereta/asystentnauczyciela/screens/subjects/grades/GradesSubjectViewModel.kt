package com.bereta.asystentnauczyciela.screens.subjects.grades

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bereta.asystentnauczyciela.room.DAO.StudentWithGradesDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject

class GradesSubjectViewModel(
    application: Application,
    subjectInput: Subject
):
    ViewModel() {
    private val studentWithGradesDAO : StudentWithGradesDAO = AssistantDatabase.getInstance(application).studentWithGradesDAO
    var currentSubjectStudents: LiveData<List<Student>> = studentWithGradesDAO.getStudents(subjectInput.ID)
    fun setSubjectStudents(id: Long) {
        currentSubjectStudents = studentWithGradesDAO.getStudents(id)
    }



}