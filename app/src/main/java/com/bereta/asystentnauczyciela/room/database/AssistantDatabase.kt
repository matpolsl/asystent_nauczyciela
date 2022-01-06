package com.bereta.asystentnauczyciela.room.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bereta.asystentnauczyciela.room.DAO.*
import com.bereta.asystentnauczyciela.room.entities.*

@Database(entities=[Subject::class,Student::class,StudentSubjects::class, StudentGrade::class,Grade::class], version = 8, exportSchema = false)
abstract class AssistantDatabase: RoomDatabase() {
    abstract val studentWithGradesDAO: StudentWithGradesDAO
    abstract val subjectsDAO: SubjectsDAO
    abstract val studentsDAO: StudentsDAO
    abstract val studentWithSubjectsDAO: StudentWithSubjectsDAO
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