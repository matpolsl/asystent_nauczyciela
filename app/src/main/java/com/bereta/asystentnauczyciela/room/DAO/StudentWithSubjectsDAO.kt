package com.bereta.asystentnauczyciela.room.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.relation.StudentWithSubjects

@Dao
interface StudentWithSubjectsDAO {
    @Transaction
    @Query("SELECT * FROM students_table")
    fun getStudentsWithSubjects(): List<StudentWithSubjects>
    @Insert
    fun insertSubjectStudent(student: Student, subjects: List<Subject>)
}