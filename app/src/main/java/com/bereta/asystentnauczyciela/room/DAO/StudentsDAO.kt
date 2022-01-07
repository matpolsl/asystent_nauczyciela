package com.bereta.asystentnauczyciela.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bereta.asystentnauczyciela.room.entities.Student

@Dao
interface StudentsDAO {
    @Insert
    fun insertStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Query("SELECT * FROM students_table")
    fun getAll(): LiveData<List<Student>>

    @Transaction
    @Query("DELETE FROM students_table")
    fun deleteAllStudents()

    @Transaction
    @Query("DELETE FROM students_subjects_table")
    fun deleteAllStudentsRelation()
}