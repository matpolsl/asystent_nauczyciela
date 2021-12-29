package com.bereta.asystentnauczyciela.screens.subjects.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bereta.asystentnauczyciela.room.DAO.SubjectsDAO
import com.bereta.asystentnauczyciela.room.database.AssistantDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.bereta.asystentnauczyciela.room.entities.Subject
class SubjectsListViewModel(
    application: Application
):
    AndroidViewModel(application) {

    private val subjectsDAO : SubjectsDAO = AssistantDatabase.getInstance(application).subjectsDAO
    val subjects: LiveData<List<Subject>> = subjectsDAO.getAll()



    fun deleteSubject(subject: Subject)
    {
        
        viewModelScope.launch(Dispatchers.IO) {
            subjectsDAO.deleteSubject(subject)
        }
    }


}