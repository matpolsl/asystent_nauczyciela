package com.bereta.asystentnauczyciela.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity(tableName = "students_subjects_table")
//Parcelable annotation to make parcelable object
@Parcelize
data class StudentWithSubjects(
    val student: Student,
    @ColumnInfo(name="subject")
    val subjects: List<Subject>
): Parcelable