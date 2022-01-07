package com.bereta.asystentnauczyciela.room.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.bereta.asystentnauczyciela.room.entities.Grade
import com.bereta.asystentnauczyciela.room.entities.Subject


data class SubjectWithGrades(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectID",
        entityColumn = "gradeID",
        associateBy = Junction(Grade::class)
    )
    val grades: List<Grade>
)