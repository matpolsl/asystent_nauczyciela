package com.bereta.asystentnauczyciela.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "grades_subject_table")
//Parcelable annotation to make parcelable object
@Parcelize
data class SubjectGrade(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="gradesID")
    var ID: Long=0L,
    @ColumnInfo(name="subjectID")
    var subjectID: Long=0L,
    @ColumnInfo(name="name")
    var name:String,
    @ColumnInfo(name="weight")
    var weight:Int,
): Parcelable