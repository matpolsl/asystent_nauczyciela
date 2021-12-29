package com.bereta.asystentnauczyciela.screens.subjects.list


import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel


class SubjectsList : Fragment() {
    lateinit var viewModel: SubjectsListViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels<SharedViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subjects_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory=SubjectsListViewModelFactory((requireNotNull(this.activity).application))
        viewModel= ViewModelProvider(requireActivity(),factory)[SubjectsListViewModel::class.java]
        val subjectsListAdapter=SubjectsListAdapter(viewModel.subjects,viewModel,sharedViewModel)

        viewModel.subjects.observe(viewLifecycleOwner,
            Observer<List<Subject>> { subjectsListAdapter.notifyDataSetChanged() }
        )

        val layoutManager= LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.recyclerView).let {

            it.adapter=subjectsListAdapter
            it.layoutManager=layoutManager
        }

        (view.findViewById<Button>(R.id.fab_add)).setOnClickListener{

            it.findNavController().navigate(R.id.action_subjectsList_to_addSubject)
        }



    }

}