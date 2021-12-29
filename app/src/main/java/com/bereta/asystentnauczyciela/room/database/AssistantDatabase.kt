package com.bereta.asystentnauczyciela.room.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bereta.asystentnauczyciela.room.DAO.StudentsDAO
import com.bereta.asystentnauczyciela.room.DAO.SubjectsDAO
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject

@Database(entities=[Subject::class,Student::class], version = 3, exportSchema = false)
abstract class AssistantDatabase: RoomDatabase() {
    abstract val subjectsDAO: SubjectsDAO
    abstract val studentsDAO: StudentsDAO
    companion object{

        @Volatile
        private var INSTANCE: AssistantDatabase? = null

        fun getInstance(context: Context): AssistantDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AssistantDatabase::class.java,
                        "assistant_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}