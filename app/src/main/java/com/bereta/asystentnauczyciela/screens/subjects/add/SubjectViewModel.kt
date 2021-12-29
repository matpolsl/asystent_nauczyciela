package com.bereta.asystentnauczyciela.screens.subjects.add

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.SubjectsDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.DayOfWeek

class SubjectViewModel(application: Application): AndroidViewModel(application)
{
    private val subjectsDAO : SubjectsDAO

    init{
        subjectsDAO= AssistantDatabase.getInstance(application).subjectsDAO
    }
    fun addSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            subjectsDAO.insertSubject(subject)
        }
        val name = subject.name
        Toast.makeText(getApplication(),"Dodano przedmiot o nazwie: $name",Toast.LENGTH_LONG).show()
    }
}