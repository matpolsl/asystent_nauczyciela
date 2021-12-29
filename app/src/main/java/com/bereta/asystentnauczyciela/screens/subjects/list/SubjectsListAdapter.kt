package com.bereta.asystentnauczyciela.screens.subjects.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.bereta.asystentnauczyciela.R
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.screens.subjects.SharedViewModel


class SubjectsListAdapter(private val subject: LiveData<List<Subject>>, private val viewModel: SubjectsListViewModel, private val sharedViewModel: SharedViewModel)
    : RecyclerView.Adapter<SubjectsListAdapter.SubjectsListHolder>()
{


    inner class SubjectsListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textViewName=view.findViewById<TextView>(R.id.name)
        val textViewDate=view.findViewById<TextView>(R.id.date)
        val buttonShow=view.findViewById<Button>(R.id.button_show_subject)
        val buttonDel=view.findViewById<Button>(R.id.button_delete_subject)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsListHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.row_subjects_list,parent,false)
        return SubjectsListHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectsListHolder, position: Int) {
        val date = subject.value?.get(position)?.dayOfWeek + " " + subject.value?.get(position)?.timeFrom + "-" + subject.value?.get(position)?.timeTo
        holder.textViewName.text=subject.value?.get(position)?.name
        holder.textViewDate.text=date

        holder.buttonDel.setOnClickListener {

            subject.value?.let{ existingSubject->
               viewModel.deleteSubject(existingSubject.get(position))

            }

        }
        holder.buttonShow.setOnClickListener {
            //model = ViewModelProvider(SubjectsListViewModel()).get(SharedViewModel::class.java)
            subject.value?.let{ existingSubject->
                sharedViewModel.select(existingSubject.get(position))
                it.findNavController().navigate(R.id.action_subjectsList_to_subject)
            }

        }
    }

    override fun getItemCount()=subject.value?.size?:0

}