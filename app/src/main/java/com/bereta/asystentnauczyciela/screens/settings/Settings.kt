package com.bereta.asystentnauczyciela.screens.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Grade
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.relation.StudentWithSubjects
import com.bereta.asystentnauczyciela.room.relation.SubjectWithGrades
import com.bereta.asystentnauczyciela.room.relation.SubjectWithStudents
import com.bereta.asystentnauczyciela.screens.students.add.AddStudentViewModel

class Settings : Fragment() {
    private lateinit var viewModel: SettingsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        (view.findViewById<Button>(R.id.delete_grades)).setOnClickListener{
            viewModel.deleteGrade()
        }
        (view.findViewById<Button>(R.id.delete_database)).setOnClickListener{
            viewModel.deleteAll()
        }
        (view.findViewById<Button>(R.id.delete_students)).setOnClickListener{
            viewModel.deleteStudents()
        }
        (view.findViewById<Button>(R.id.delete_subjects)).setOnClickListener{
            viewModel.deleteSubjects()
        }
        (view.findViewById<Button>(R.id.raport)).setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, viewModel.raport())
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "Raport")
            startActivity(shareIntent)
        }
        viewModel.students.observe(viewLifecycleOwner,
            Observer<List<Student>> {  }
        )
        viewModel.grades.observe(viewLifecycleOwner,
            Observer<List<SubjectWithGrades>> {  }
        )
    }
}