package com.bereta.asystentnauczyciela.screens.subjects.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel
import com.bereta.asystentnauczyciela.screens.subjects.list.SubjectsListAdapter
import com.bereta.asystentnauczyciela.screens.subjects.list.SubjectsListViewModel
import com.bereta.asystentnauczyciela.screens.subjects.list.SubjectsListViewModelFactory


class Subject : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels<SharedViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textViewName=view.findViewById<TextView>(R.id.subject_name)
        val textViewDate=view.findViewById<TextView>(R.id.day_text)
        //val buttonShow=view.findViewById<Button>(R.id.button_show_subject)
        //val buttonDel=view.findViewById<Button>(R.id.button_delete_subject)
        val test = sharedViewModel.get()
        if (test != null) {
            textViewName.text = test.name
            textViewDate.text = test.dayOfWeek
            //Toast.makeText(this,"Dodano przedmiot o nazwie: $name", Toast.LENGTH_LONG).show()

        }

    }

}