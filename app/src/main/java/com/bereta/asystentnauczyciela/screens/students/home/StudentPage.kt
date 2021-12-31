package com.bereta.asystentnauczyciela.screens.students.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bereta.asystentnauczyciela.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class StudentPage : Fragment() {

    private lateinit var collectionAdapter: PagerAdapterStudent
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectionAdapter = PagerAdapterStudent(this)
        viewPager2 = view.findViewById(R.id.pagerStudent)
        viewPager2.adapter = collectionAdapter
        tabLayout = view.findViewById(R.id.tabLayoutStudent)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when(position){
                0 -> tab.text = "Edycja\nStudenta"
                1 -> tab.text = "Dodawanie\nPrzedmiotów"
                2 -> tab.text = "Usuwanie\nPrzedmiotów"
            }
        }.attach()
    }
}
