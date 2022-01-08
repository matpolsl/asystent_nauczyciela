package com.bereta.asystentnauczyciela.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bereta.asystentnauczyciela.room.entities.Subject


@Dao
interface SubjectsDAO {
    @Insert
    fun insertSubject(subject: Subject)

    @Update
    fun updateSubject(subject: Subject)

    @Delete
    fun deleteSubject(subject: Subject)

    @Query("SELECT * FROM subjects_table")
    fun getAll(): LiveData<List<Subject>>

    @Transaction
    @Query("DELETE FROM subjects_table")
    fun deleteAllSubjects()
}