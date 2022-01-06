package com.bereta.asystentnauczyciela.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bereta.asystentnauczyciela.room.entities.StudentGrade
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade
import com.bereta.asystentnauczyciela.room.models.GradesAndStudents
import com.bereta.asystentnauczyciela.room.relation.SubjectWithGrades

@Dao
interface StudentWithGradesDAO {
    @Transaction
    @Query("SELECT S.*, G.gradeID,G.gradesID, G.Grade, G.note from (SELECT S.* FROM students_table S left join students_subjects_table P on S.studentID = P.studentID WHERE P.subjectID LIKE :idSubject) as S "+
            "left join grades_students_table G on S.studentID = G.studentID where G.gradesID LIKE :idGrade OR G.gradesID is null")
    fun getGradesStudents(idSubject: Long,idGrade: Long): LiveData<List<GradesAndStudents>>
    @Insert
    fun insertGrade(grade: StudentGrade)
    @Update
    fun updateGrade(grade: StudentGrade)
}