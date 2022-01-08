package com.bereta.asystentnauczyciela.screens.subjects.edit

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.SubjectsDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import com.bereta.asystentnauczyciela.room.entities.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditSubjectViewModel(application: Application): AndroidViewModel(application)
{
    private val subjectsDAO : SubjectsDAO = AssistantDatabase.getInstance(application).subjectsDAO

    fun editSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            subjectsDAO.updateSubject(subject)
        }
        val name = subject.name
        Toast.makeText(getApplication(),"Zaktualizowano przedmiot o nazwie: $name", Toast.LENGTH_LONG).show()
    }
}