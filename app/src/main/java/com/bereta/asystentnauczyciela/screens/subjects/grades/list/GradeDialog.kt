package com.bereta.asystentnauczyciela.screens.subjects.grades.list

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Grade
import com.bereta.asystentnauczyciela.room.entities.StudentGrade
import com.bereta.asystentnauczyciela.room.relation.SubjectWithGrades
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel

class GradeDialog: DialogFragment() {
    private lateinit var viewModel: GradeDialogViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            viewModel = ViewModelProvider(this)[GradeDialogViewModel::class.java]
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.dialog_grade, null))
                .setTitle("Ocena "+ sharedViewModel.getStudent()?.firstName.toString()+" "+ sharedViewModel.getStudent()?.lastName.toString())
                // Add action buttons
                .setPositiveButton("Dodaj",
                    DialogInterface.OnClickListener { _, _ ->
                        onDialogPositiveClickAdd()
                    })
                .setNegativeButton("Anuluj",
                    DialogInterface.OnClickListener { _, _ ->
                        dialog?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun onDialogPositiveClickAdd() {
        val grade = dialog?.findViewById<EditText>(R.id.grade_value)?.text.toString().toDouble()
        val note = dialog?.findViewById<EditText>(R.id.note_value)?.text.toString()
        val subject = sharedViewModel.get()
        val student = sharedViewModel.getStudent()
        val newGrade = subject?.let { student?.let { it1 ->
            Grade(0, it.ID,
                it1.studentID,grade,note)
        } }
        if (newGrade != null) {
            viewModel.addGrade(newGrade)
        }
    }
}
