package com.bereta.asystentnauczyciela.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade

@Dao
interface SubjectWithGradesDAO {
    @Transaction
    @Query("SELECT * FROM grades_subject_table "+
            "WHERE subjectID LIKE :id")
    fun getGradesSubject(id: Long): LiveData<List<SubjectGrade>>
    @Insert
    fun insertGradeSubject(grade: SubjectGrade)
    @Delete
    fun deleteGradeSubject(grade: SubjectGrade)
   // @Transaction
   // @Query("SELECT S.*, count(*) as count FROM students_subjects_table  "+
   //         "WHERE subjectID LIKE :id")
   // fun getCountStudents(id: Long): Count
    //@Transaction
    //@Query("SELECT S.*, count(G.gradeID) as count FROM grades_students_table G inner join subjects_table S on G.subjectID = S.subjectID "+
     //       "WHERE G.gradesID LIKE :id")
    //fun getCountGrades(id: Long): Count
}
