package com.bereta.asystentnauczyciela.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "subjects_table")
//Parcelable annotation to make parcelable object
@Parcelize
data class Subject(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var ID: Long=0L,
    @ColumnInfo(name="name")
    var name:String,
    @ColumnInfo(name="day_of_week")
    var dayOfWeek:String,
    @ColumnInfo(name="time_from")
    var timeFrom: String,
    @ColumnInfo(name="time_to")
    var timeTo:String
):Parcelable
