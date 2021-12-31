package com.bereta.asystentnauczyciela.room.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.StudentSubjects
import com.bereta.asystentnauczyciela.room.entities.Subject


data class SubjectWithStudents(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectID",
        entityColumn = "studentID",
        associateBy = Junction(StudentSubjects::class)
    )
    val students: List<Student>
)