package com.bereta.asystentnauczyciela.room.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.StudentGrade
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade

data class GradeWithGrades(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentID",
        entityColumn = "gradesID",
        associateBy = Junction(SubjectGrade::class)
    )
    val grades: List<StudentGrade>
)