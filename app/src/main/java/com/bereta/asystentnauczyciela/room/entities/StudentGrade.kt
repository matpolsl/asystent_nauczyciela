package com.bereta.asystentnauczyciela.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "grades_students_table")
//Parcelable annotation to make parcelable object
@Parcelize
data class StudentGrade(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="gradeID")
    var ID: Long=0L,

): Parcelable