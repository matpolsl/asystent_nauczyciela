package com.bereta.asystentnauczyciela.screens.subjects.grades

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.SubjectWithGradesDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradesSubjectViewModel(
    application: Application,
    subjectInput: Subject
):
    ViewModel() {
    private val subjectWithGradesDAO : SubjectWithGradesDAO = AssistantDatabase.getInstance(application).subjectWithGradesDAO
    var currentSubjectGrades: LiveData<List<SubjectGrade>> = subjectWithGradesDAO.getGradesSubject(subjectInput.ID)
    fun setSubjectGrades(id: Long) {
        currentSubjectGrades = subjectWithGradesDAO.getGradesSubject(id)
    }

    fun rmSubjectGrades(grade: SubjectGrade){
        viewModelScope.launch(Dispatchers.IO){
            subjectWithGradesDAO.deleteGradeSubject(grade)
        }
    }

}