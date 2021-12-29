package com.bereta.asystentnauczyciela.screens.students.list


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.screens.students.add.AddStudent
import com.bereta.asystentnauczyciela.screens.subjects.list.SubjectsListAdapter
import com.bereta.asystentnauczyciela.screens.subjects.list.SubjectsListViewModel



class StudentsList : Fragment() {
    lateinit var viewModel: StudentsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_students_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = StudentsListViewModelFactory((requireNotNull(this.activity).application))
        viewModel= ViewModelProvider(requireActivity(),factory)[StudentsListViewModel::class.java]
        val studentsListAdapter= StudentsListAdapter(viewModel.students,viewModel)

        viewModel.students.observe(viewLifecycleOwner,
            Observer<List<Student>> { studentsListAdapter.notifyDataSetChanged() }
        )
        Log.d("SQL",viewModel.students.value.toString())
        val layoutManager= LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.recyclerView_students).let {

            it.adapter=studentsListAdapter
            it.layoutManager=layoutManager
        }
        (view.findViewById<Button>(R.id.fab_add_student)).setOnClickListener{
            val createDialog = AddStudent()
            createDialog.show(childFragmentManager, "NoticeDialogFragment")

        }
    }




}