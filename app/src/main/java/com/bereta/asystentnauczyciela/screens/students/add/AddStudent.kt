package com.bereta.asystentnauczyciela.screens.students.add

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Student


class AddStudent : DialogFragment() {
    private lateinit var viewModel: AddStudentViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            viewModel = ViewModelProvider(this)[AddStudentViewModel::class.java]
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.dialog_student_add, null))
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
        val id = dialog?.findViewById<EditText>(R.id.numberID)?.text.toString().toInt()
        val firstName = dialog?.findViewById<EditText>(R.id.first_name)?.text.toString()
        val lastName = dialog?.findViewById<EditText>(R.id.last_name)?.text.toString()
        val student = Student(id,firstName,lastName)
        viewModel.addStudent(student)
    }

}