package com.bereta.asystentnauczyciela.room.entities;

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "students_table")
//Parcelable annotation to make parcelable object
@Parcelize
data class Student(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id")
    var ID: Int,
    @ColumnInfo(name="first_name")
    var firstName:String,
    @ColumnInfo(name="last_name")
    var lastName:String,
):Parcelable