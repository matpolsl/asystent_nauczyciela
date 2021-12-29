package com.bereta.asystentnauczyciela.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bereta.asystentnauczyciela.room.entities.Subject


@Dao
interface SubjectsDAO {
    @Insert
    fun insertSubject(subject: Subject)

    @Delete
    fun deleteSubject(subject: Subject)

    @Query("SELECT * FROM subjects_table")
    fun getAll(): LiveData<List<Subject>>
}