package com.bereta.asystentnauczyciela.screens.students.edit.subjects

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.StudentWithSubjectsDAO
import com.bereta.asystentnauczyciela.room.DAO.SubjectsDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.relation.StudentWithSubjects
import com.bereta.asystentnauczyciela.screens.students.SharedViewModelStudent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectsStudentViewModel(
    application: Application,
    private val sharedViewModel: SharedViewModelStudent
):
    AndroidViewModel(application) {
    private val student = sharedViewModel.get()
    private val studentWithSubjectsDAO : StudentWithSubjectsDAO = AssistantDatabase.getInstance(application).studentWithSubjectsDAO
    private val subjectsDAO : SubjectsDAO = AssistantDatabase.getInstance(application).subjectsDAO
    val subjectsJoined: LiveData<List<Subject>> = studentWithSubjectsDAO.getStudentWithSubjects(student!!.ID)
    val subjectsNotJoined: LiveData<List<Subject>> = studentWithSubjectsDAO.getStudentWithNotSubjects(student!!.ID)
    private val subjectTempAdd: MutableList<Subject> = mutableListOf()
    private val subjectTempRm: MutableList<Subject> = mutableListOf()
    fun addSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            if (student != null) {
                studentWithSubjectsDAO.insertStudentSubject(student.ID,subject.ID)
                //studentWithSubjectsDAO.insertStudentSubject2(student,subject)
            }
        }
    }
}