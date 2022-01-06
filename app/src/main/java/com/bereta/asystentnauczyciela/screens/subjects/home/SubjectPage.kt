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
import androidx.viewpager2.widget.ViewPager2
import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.screens.students.home.PagerAdapterStudent
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel
import com.bereta.asystentnauczyciela.screens.subjects.list.SubjectsListAdapter
import com.bereta.asystentnauczyciela.screens.subjects.list.SubjectsListViewModel
import com.bereta.asystentnauczyciela.screens.subjects.list.SubjectsListViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class SubjectPage : Fragment() {
    private lateinit var collectionAdapter: PagerAdapterSubject
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectionAdapter = PagerAdapterSubject(this)
        viewPager2 = view.findViewById(R.id.pagerSubject)
        viewPager2.adapter = collectionAdapter
        tabLayout = view.findViewById(R.id.tabLayoutSubject)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when(position){
                0 -> tab.text = "Lista Ocen"
                1 -> tab.text = "Edycja Przedmiotu"
            }
        }.attach()
    }

}