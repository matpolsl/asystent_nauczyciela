package com.bereta.asystentnauczyciela.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bereta.asystentnauczyciela.room.entities.Student

@Dao
interface StudentsDAO {
    @Insert
    fun insertStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Query("SELECT * FROM students_table")
    fun getAll(): LiveData<List<Student>>
}