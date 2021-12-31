package com.bereta.asystentnauczyciela.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "students_subjects_table", primaryKeys = ["studentID", "subjectID"])
//Parcelable annotation to make parcelable object
@Parcelize
data class StudentSubjects(
    val studentID: Int,
    val subjectID: Long
): Parcelable