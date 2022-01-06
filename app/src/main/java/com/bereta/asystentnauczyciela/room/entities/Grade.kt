package com.bereta.asystentnauczyciela.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "grades_table")
//Parcelable annotation to make parcelable object
@Parcelize
data class Grade(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="gradeID")
    var ID: Long=0L,
    @ColumnInfo(name="subjectID")
    var subjectID: Long,
    @ColumnInfo(name="studentID")
    var studentID: Int,
    @ColumnInfo(name="grade")
    var grade:Double,
    @ColumnInfo(name="note")
    var note:String,
): Parcelable