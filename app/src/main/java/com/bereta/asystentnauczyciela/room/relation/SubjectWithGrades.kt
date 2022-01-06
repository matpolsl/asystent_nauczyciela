package com.bereta.asystentnauczyciela.room.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.StudentSubjects
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade


data class SubjectWithGrades(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectID",
        entityColumn = "gradesID",
        associateBy = Junction(SubjectGrade::class)
    )
    val subjects: List<SubjectGrade>
)