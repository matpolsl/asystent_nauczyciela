package com.bereta.asystentnauczyciela.screens.subjects.add

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Subject
import android.widget.AdapterView
import java.time.LocalTime


class AddSubject : Fragment() {
    private var dayOfWeek = "Poniedziałek" // default value
    lateinit var viewModel: SubjectViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel= ViewModelProvider(this)
            .get(SubjectViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_subject, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TimePicker>(R.id.time_picker_from).setIs24HourView(true)
        view.findViewById<TimePicker>(R.id.time_picker_to).setIs24HourView(true)
        val spinner: Spinner = view.findViewById(R.id.day_spinner)
        ArrayAdapter.createFromResource(
            this.activity!!.applicationContext,
            R.array.week_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        (view.findViewById<Button>(R.id.button)).setOnClickListener{
            val timeFrom = LocalTime.of(view.findViewById<TimePicker>(R.id.time_picker_from).hour, view.findViewById<TimePicker>(R.id.time_picker_from).minute)
            val timeTo = LocalTime.of(view.findViewById<TimePicker>(R.id.time_picker_to).hour,view.findViewById<TimePicker>(R.id.time_picker_to).minute)
            val name = view.findViewById<EditText>(R.id.edit_name).text.toString()
            val subject = Subject(0,name,dayOfWeek,timeFrom.toString(),timeTo.toString())
            viewModel.addSubject(subject)
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
