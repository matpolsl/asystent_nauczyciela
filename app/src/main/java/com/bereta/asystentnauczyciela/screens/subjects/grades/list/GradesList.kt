package com.bereta.asystentnauczyciela.screens.subjects.grades.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Grade
import com.bereta.asystentnauczyciela.screens.students.add.AddStudent
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel

class GradesList: Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels<SharedViewModel>()
    lateinit var viewModel: GradesListViewModel
    lateinit var factory: GradesListViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grades_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val student = sharedViewModel.getStudent()
        val textViewName=view.findViewById<TextView>(R.id.name_student)
        if (student != null) {
            val name = student.firstName + " " + student.lastName
            textViewName.text = name
        }
        val subject = sharedViewModel.get()
        factory = GradesListViewModelFactory((requireNotNull(this.activity).application),subject!!,student!!)
        viewModel= ViewModelProvider(requireActivity(),factory)[GradesListViewModel::class.java]
        student?.let {
            if (subject != null) {
                viewModel.setStudentGrades(subject.ID,it.studentID)
            }
        }
        val gradesListAdapter = GradesListAdapter(viewModel.currentStudentGrades,viewModel,sharedViewModel,childFragmentManager)
        viewModel.currentStudentGrades.observe(viewLifecycleOwner,
            Observer<List<Grade>> { gradesListAdapter.notifyDataSetChanged() }
        )
        val layoutManager= LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.recyclerView_grades).let {
            it.adapter=gradesListAdapter
            it.layoutManager=layoutManager
        }
        (view.findViewById<Button>(R.id.fab_add_grade)).setOnClickListener{
            val createDialog = GradeDialog()
            createDialog.show(childFragmentManager, "NoticeDialogFragment")

        }
    }
}