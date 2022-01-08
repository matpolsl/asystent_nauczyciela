package com.bereta.asystentnauczyciela.screens.subjects.edit

import android.os.Build
import android.os.Bundle
import android.text.Selection.setSelection
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel
import com.bereta.asystentnauczyciela.screens.subjects.add.SubjectViewModel
import java.time.LocalTime


class EditSubject : Fragment() {
    lateinit var viewModel: EditSubjectViewModel
    lateinit var dayOfWeek: String
    private val sharedViewModel: SharedViewModel by activityViewModels<SharedViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel= ViewModelProvider(this)
            .get(EditSubjectViewModel::class.java)
        return inflater.inflate(R.layout.fragment_edit_subject, container, false)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val subjectOld = sharedViewModel.get()
        val timePickerFrom = view.findViewById<TimePicker>(R.id.time_picker_from)
        val timePickerTo = view.findViewById<TimePicker>(R.id.time_picker_to)
        timePickerFrom.setIs24HourView(true)
        timePickerTo.setIs24HourView(true)
        val name = view.findViewById<TextView>(R.id.textField_name)
        val spinner: Spinner = view.findViewById(R.id.day_spinner)
        ArrayAdapter.createFromResource(
            this.activity!!.applicationContext,
            R.array.week_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        if (subjectOld != null) {
            name.text = subjectOld.name
            val timeFrom = subjectOld.timeFrom.split(":")
            val timeTo = subjectOld.timeTo.split(":")
            timePickerFrom.hour = timeFrom[0].toInt()
            timePickerFrom.minute = timeFrom[1].toInt()
            timePickerTo.hour = timeTo[0].toInt()
            timePickerTo.minute = timeTo[1].toInt()
            val dayOfWeekList = listOf<String>("Poniedziałek","Wtorek","Środa","Czwartek","Piątek","Sobota","Niedziela")
            var indexSelection = dayOfWeekList.indexOf(subjectOld.dayOfWeek)
            spinner.setSelection(indexSelection)
        }
        (view.findViewById<Button>(R.id.button)).setOnClickListener{
            val timeFrom = LocalTime.of(view.findViewById<TimePicker>(R.id.time_picker_from).hour, view.findViewById<TimePicker>(R.id.time_picker_from).minute)
            val timeTo = LocalTime.of(view.findViewById<TimePicker>(R.id.time_picker_to).hour,view.findViewById<TimePicker>(R.id.time_picker_to).minute)
            val name = view.findViewById<TextView>(R.id.textField_name).text.toString()

            val subject = subjectOld?.let { it1 -> Subject(it1.ID,name,dayOfWeek,timeFrom.toString(),timeTo.toString()) }
            if (subject != null) {
                viewModel.editSubject(subject)
                sharedViewModel.select(subject)
            }
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                dayOfWeek = parent.getItemAtPosition(pos).toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }
    }
}