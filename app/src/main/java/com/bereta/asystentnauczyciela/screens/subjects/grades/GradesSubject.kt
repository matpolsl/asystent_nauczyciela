package com.bereta.asystentnauczyciela.screens.subjects.grades

import android.os.Bundle
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
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.entities.SubjectGrade
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel
import com.bereta.asystentnauczyciela.screens.subjects.grades.add.AddGradesSubject

class GradesSubject : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels<SharedViewModel>()
    lateinit var viewModel: GradesSubjectViewModel
    lateinit var factory: GradesSubjectViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject_grades, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textViewName=view.findViewById<TextView>(R.id.name_subject)
        val textViewDay=view.findViewById<TextView>(R.id.day_subject)
        val textViewTime=view.findViewById<TextView>(R.id.time_subject)
        val subject: Subject? = sharedViewModel.get()
        if (subject != null) {
            textViewName.text = subject.name
            textViewDay.text = subject.dayOfWeek
            textViewTime.text = subject.timeFrom+" - "+subject.timeTo
        }
        (view.findViewById<Button>(R.id.fab_add_grade_subject)).setOnClickListener{
            val createDialog = AddGradesSubject()
            createDialog.show(childFragmentManager, "NoticeDialogFragment")
        }
        factory = GradesSubjectViewModelFactory((requireNotNull(this.activity).application),sharedViewModel.get()!!)
        viewModel= ViewModelProvider(requireActivity(),factory)[GradesSubjectViewModel::class.java]
        sharedViewModel.get()?.let { viewModel.setSubjectGrades(it.ID) }
        val gradesSubjectAdapter = GradesSubjectAdapter(viewModel.currentSubjectGrades,viewModel)
        viewModel.currentSubjectGrades.observe(viewLifecycleOwner,
            Observer<List<SubjectGrade>> { gradesSubjectAdapter.notifyDataSetChanged() }
        )
        val layoutManager= LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.recyclerView_grades_subject).let {
            it.adapter=gradesSubjectAdapter
            it.layoutManager=layoutManager
        }



    }
}