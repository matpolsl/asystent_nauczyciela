package com.bereta.asystentnauczyciela.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade
import com.bereta.asystentnauczyciela.room.relation.SubjectWithGrades

@Dao
interface StudentWithGradesDAO {
    //@Transaction
   // @Query("SELECT * FROM grades_subject_table "+
    //        "WHERE subjectID LIKE :id")
   // fun getGradesSubject(id: Long): LiveData<SubjectWithGrades>
   // @Insert
   // fun insertGradeSubject(grade: SubjectGrade)

}