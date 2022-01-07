package com.bereta.asystentnauczyciela.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bereta.asystentnauczyciela.room.entities.Grade
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.relation.SubjectWithGrades

@Dao
interface StudentWithGradesDAO {
    @Transaction
    @Query("SELECT S.* FROM students_table S left join students_subjects_table P on S.studentID = P.studentID WHERE P.subjectID LIKE :idSubject")
    fun getStudents(idSubject: Long): LiveData<List<Student>>
    @Transaction
    @Query("SELECT G.*,S.* FROM grades_table G inner join subjects_table S on G.subjectID=S.subjectID")
    fun getAll(): LiveData<List<SubjectWithGrades>>
    @Transaction
    @Query("SELECT G.* FROM grades_table G where G.subjectID like :idSubject and G.studentID LIKE :idStudent")
    fun getGrades(idSubject: Long,idStudent: Int): LiveData<List<Grade>>
    @Insert
    fun inseretGrade(grade: Grade)
    @Delete
    fun deleteGrade(grade: Grade)
    @Transaction
    @Query("DELETE FROM grades_table")
    fun deleteAllGrade()
}