package com.bereta.asystentnauczyciela.screens.students.edit.subjects

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.bereta.asystentnauczyciela.room.DAO.StudentWithSubjectsDAO
import com.bereta.asystentnauczyciela.room.DAO.SubjectsDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.screens.students.SharedViewModelStudent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectsStudentViewModel(
    application: Application,
    studentInput: Student
):
    ViewModel() {
    private val studentWithSubjectsDAO : StudentWithSubjectsDAO = AssistantDatabase.getInstance(application).studentWithSubjectsDAO
    //private val _student = MutableLiveData<Student>()

    val student = MutableLiveData(studentInput)
    fun loadFeeds(value: Student) {
        student.value = value
        //livedata gets fired even though the value is not changed
    }
    private fun getCurrentStudent() = student.value!!
    var currentSubjectsJoined: LiveData<List<Subject>> = studentWithSubjectsDAO.getStudentWithSubjects(getCurrentStudent().studentID)
    var currentSubjectsNotJoined: LiveData<List<Subject>> = studentWithSubjectsDAO.getStudentWithSubjects(getCurrentStudent().studentID)
    private fun getSubjectsJoined(id: Int) {
        currentSubjectsJoined = studentWithSubjectsDAO.getStudentWithSubjects(id)
    }
    private fun getSubjectsNotJoined(id: Int) {
        currentSubjectsNotJoined = studentWithSubjectsDAO.getStudentWithNotSubjects(id)
    }
    fun update(id: Int){
        getSubjectsJoined(id)
        getSubjectsNotJoined(id)
    }
    fun addSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            studentWithSubjectsDAO.insertStudentSubject(getCurrentStudent().studentID,subject.ID)
            Log.d("MVVM",getCurrentStudent().studentID.toString())
        //studentWithSubjectsDAO.insertStudentSubject2(student,subject)
        }
    }
    fun rmSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            studentWithSubjectsDAO.deleteStudentSubject(getCurrentStudent().studentID,subject.ID)
        //studentWithSubjectsDAO.insertStudentSubject2(student,subject)

        }
    }

}