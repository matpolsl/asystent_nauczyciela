package com.bereta.asystentnauczyciela.screens.subjects.grades.add

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel

class AddGradesSubject: DialogFragment() {
    private lateinit var viewModel: AddGradesSubjectViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            viewModel = ViewModelProvider(this)[AddGradesSubjectViewModel::class.java]
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.dialog_grades_subject, null))
                // Add action buttons
                .setPositiveButton("Dodaj",
                    DialogInterface.OnClickListener { _, _ ->
                        onDialogPositiveClick()
                    })
                .setNegativeButton("Anuluj",
                    DialogInterface.OnClickListener { _, _ ->
                        dialog?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun onDialogPositiveClick() {
        val weight = dialog?.findViewById<EditText>(R.id.weight)?.text.toString().toInt()
        val name = dialog?.findViewById<EditText>(R.id.name_grades)?.text.toString()
        val subject = sharedViewModel.get()
        val grades = subject?.let { SubjectGrade(0, it.ID, name, weight) }
        if (grades != null) {
            viewModel.addSubjectGrades(grades)
        }
    }
}
