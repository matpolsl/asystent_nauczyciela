package com.bereta.asystentnauczyciela.screens.settings

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.StudentWithGradesDAO
import com.bereta.asystentnauczyciela.room.DAO.StudentWithSubjectsDAO
import com.bereta.asystentnauczyciela.room.DAO.StudentsDAO
import com.bereta.asystentnauczyciela.room.DAO.SubjectsDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Grade
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.relation.StudentWithSubjects
import com.bereta.asystentnauczyciela.room.relation.SubjectWithGrades
import com.bereta.asystentnauczyciela.room.relation.SubjectWithStudents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application):
    AndroidViewModel(application) {
    private val studentsDAO: StudentsDAO = AssistantDatabase.getInstance(application).studentsDAO
    private val studentWithGradesDAO: StudentWithGradesDAO =
        AssistantDatabase.getInstance(application).studentWithGradesDAO
    private val studentWithSubjectsDAO: StudentWithSubjectsDAO =
        AssistantDatabase.getInstance(application).studentWithSubjectsDAO
    private val subjectsDAO: SubjectsDAO = AssistantDatabase.getInstance(application).subjectsDAO
    fun deleteGrade() {
        viewModelScope.launch(Dispatchers.IO) {
            studentWithGradesDAO.deleteAllGrade()
        }
        Toast.makeText(getApplication(), "Usunięto oceny", Toast.LENGTH_LONG).show()
    }

    fun deleteStudents() {
        viewModelScope.launch(Dispatchers.IO) {
            studentWithGradesDAO.deleteAllGrade()
            studentsDAO.deleteAllStudents()
            studentsDAO.deleteAllStudentsRelation()
        }
        Toast.makeText(getApplication(), "Usunięto studentów", Toast.LENGTH_LONG).show()
    }

    fun deleteSubjects() {
        viewModelScope.launch(Dispatchers.IO) {
            studentWithGradesDAO.deleteAllGrade()
            subjectsDAO.deleteAllSubjects()
            studentsDAO.deleteAllStudentsRelation()
        }
        Toast.makeText(getApplication(), "Usunięto przedmioty", Toast.LENGTH_LONG).show()
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            studentWithGradesDAO.deleteAllGrade()
            studentsDAO.deleteAllStudents()
            studentsDAO.deleteAllStudentsRelation()
            subjectsDAO.deleteAllSubjects()
        }
        Toast.makeText(getApplication(), "Usunięto wszystko", Toast.LENGTH_LONG).show()
    }

    var students: LiveData<List<Student>> = studentsDAO.getAll()
    var grades: LiveData<List<SubjectWithGrades>> = studentWithGradesDAO.getAll()


    fun raport(): String {
        var respond = "";
            var lista = grades.value
            val studentList = students.value
            if (lista != null && studentList != null) {
                for (sub in lista) {
                    respond += "Przedmiot: " + sub.subject.name + " \n"
                    for (stud in sub.grades) {
                        var student = studentList.find { it.studentID == stud.studentID }
                        if (student != null)
                            respond += student.firstName + " " + student.lastName + " ocena: " + stud.grade + " notatka: " + stud.note + "\n"
                    }
                    respond += "************\n"
                }
            }
        return respond
    }
}
