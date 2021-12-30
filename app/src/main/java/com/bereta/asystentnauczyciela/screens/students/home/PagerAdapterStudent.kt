package com.bereta.asystentnauczyciela.screens.students.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bereta.asystentnauczyciela.screens.students.edit.EditStudent
import com.bereta.asystentnauczyciela.screens.students.list.StudentsList
import com.bereta.asystentnauczyciela.screens.subjects.add.AddSubject

class PagerAdapterStudent(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private lateinit var fragment:Fragment
    private val ARG_OBJECT = "object"

    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        when (position) {
            0 -> {
                 fragment = EditStudent()
             }
            1 -> {
                 fragment = AddSubject()
             }
            2 -> {
                fragment = EditStudent()
            }
        }

        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}
