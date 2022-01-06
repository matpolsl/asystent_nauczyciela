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
import com.bereta.asystentnauczyciela.room.entities.StudentGrade
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel
import com.bereta.asystentnauczyciela.screens.subjects.grades.add.AddGradesSubjectViewModel

class GradeDialog: DialogFragment() {
    private lateinit var viewModel: GradeDialogViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("test", sharedViewModel.getGrade()?.student.toString())
        return activity?.let {
            viewModel = ViewModelProvider(this)[GradeDialogViewModel::class.java]
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            if(sharedViewModel.getGrade()?.gradesID == null)
            builder.setView(inflater.inflate(R.layout.dialog_grade, null))
                .setTitle("Ocena "+ sharedViewModel.getGrade()?.student?.firstName.toString()+" "+ sharedViewModel.getGrade()?.student?.lastName.toString())
                // Add action buttons
                .setPositiveButton("Dodaj",
                    DialogInterface.OnClickListener { _, _ ->
                        onDialogPositiveClickAdd()
                    })
                .setNegativeButton("Anuluj",
                    DialogInterface.OnClickListener { _, _ ->
                        dialog?.cancel()
                    })
            else
                builder.setView(inflater.inflate(R.layout.dialog_grade, null))
                    .setTitle("Ocena "+ sharedViewModel.getGrade()?.student?.firstName.toString()+" "+ sharedViewModel.getGrade()?.student?.lastName.toString())
                    // Add action buttons
                    .setPositiveButton("Edytuj",
                        DialogInterface.OnClickListener { _, _ ->
                            onDialogPositiveClickEdit()
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
        val gradesID = sharedViewModel.getGrades()?.ID
        val sharedGrade = sharedViewModel.getGrade()
        val newGrade = gradesID?.let {
            sharedGrade?.student?.let { it1 -> StudentGrade(0, it1.studentID, it,grade,note)
            }
        }
        if (newGrade != null) {
            viewModel.addGrade(newGrade)
        }
    }
    private fun onDialogPositiveClickEdit() {
        val grade = dialog?.findViewById<EditText>(R.id.grade_value)?.text.toString().toDouble()
        val note = dialog?.findViewById<EditText>(R.id.note_value)?.text.toString()
        val gradesID = sharedViewModel.getGrades()?.ID
        val sharedGrade = sharedViewModel.getGrade()
        val oldGrade = gradesID?.let {
            sharedGrade?.student?.let { it1 ->
                sharedGrade.ID?.let { it2 -> StudentGrade(it2, it1.studentID, it,grade,note) }
            }
        }

        if (oldGrade != null) {
            viewModel.updateGrade(oldGrade)
        }
    }
}
