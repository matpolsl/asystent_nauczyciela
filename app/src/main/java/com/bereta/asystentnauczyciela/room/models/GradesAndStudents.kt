package com.bereta.asystentnauczyciela.room.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.StudentGrade

data class GradesAndStudents (
    @Embedded
    var student: Student,
    @ColumnInfo(name="gradeID")
    var ID: Long?,
    @ColumnInfo(name="gradesID")
    var gradesID: Long?,
    @ColumnInfo(name="grade")
    var grade:Double?,
    @ColumnInfo(name="note")
    var note:String?,
        )