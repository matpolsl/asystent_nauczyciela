package com.bereta.asystentnauczyciela.room.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject


data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val subjects: List<Subject>
)