package com.bereta.asystentnauczyciela.screens.subjects.grades.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.models.GradesAndStudents
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
        val gradesCategory = sharedViewModel.getGrades()
        val textViewName=view.findViewById<TextView>(R.id.name_grades_list)
        if (gradesCategory != null) {
            textViewName.text = gradesCategory.name
        }
        factory = GradesListViewModelFactory((requireNotNull(this.activity).application),sharedViewModel.getGrades()!!)
        viewModel= ViewModelProvider(requireActivity(),factory)[GradesListViewModel::class.java]
        sharedViewModel.getGrades()?.let { viewModel.setStudentGrades(it.subjectID,it.ID) }
        val gradesListAdapter = GradesListAdapter(viewModel.currentStudentGrades,viewModel,sharedViewModel,childFragmentManager)
        viewModel.currentStudentGrades.observe(viewLifecycleOwner,
            Observer<List<GradesAndStudents>> { gradesListAdapter.notifyDataSetChanged() }
        )
        Log.d("tst",viewModel.currentStudentGrades.value.toString())
        val layoutManager= LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.recyclerView_grades).let {
            it.adapter=gradesListAdapter
            it.layoutManager=layoutManager
        }
    }
}