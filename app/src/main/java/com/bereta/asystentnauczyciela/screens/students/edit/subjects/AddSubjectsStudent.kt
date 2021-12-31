package com.bereta.asystentnauczyciela.screens.students.edit.subjects

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.screens.students.SharedViewModelStudent
import com.bereta.asystentnauczyciela.screens.students.add.AddStudent
import com.bereta.asystentnauczyciela.screens.students.list.StudentsListAdapter
import com.bereta.asystentnauczyciela.screens.students.list.StudentsListViewModel
import com.bereta.asystentnauczyciela.screens.students.list.StudentsListViewModelFactory


class AddSubjectsStudent: Fragment() {
    private val sharedViewModel: SharedViewModelStudent by activityViewModels<SharedViewModelStudent>()
    lateinit var viewModel: SubjectsStudentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_subject, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = SubjectsStudentViewModelFactory((requireNotNull(this.activity).application),sharedViewModel)
        viewModel= ViewModelProvider(requireActivity(),factory)[SubjectsStudentViewModel::class.java]
        val addSubjectsStudentAdapter = AddSubjectsStudentAdapter(viewModel.subjectsNotJoined,viewModel)
        viewModel.subjectsNotJoined.observe(viewLifecycleOwner,
            Observer<List<Subject>> { addSubjectsStudentAdapter.notifyDataSetChanged() }
        )
        Log.d("SQL",viewModel.subjectsNotJoined.value.toString())
        val layoutManager= LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.recycleView_student_subjects).let {
            it.adapter=addSubjectsStudentAdapter
            it.layoutManager=layoutManager
        }
    }
}