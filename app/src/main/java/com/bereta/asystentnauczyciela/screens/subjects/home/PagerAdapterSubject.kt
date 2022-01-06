package com.bereta.asystentnauczyciela.screens.subjects.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bereta.asystentnauczyciela.screens.subjects.edit.EditSubject
import com.bereta.asystentnauczyciela.screens.subjects.grades.GradesSubject

class PagerAdapterSubject(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private lateinit var fragment: Fragment
    private val ARG_OBJECT = "object"

    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        when (position) {
            0 -> {
                fragment = GradesSubject()
            }
            1 -> {
                fragment = EditSubject()
            }
        }

        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}