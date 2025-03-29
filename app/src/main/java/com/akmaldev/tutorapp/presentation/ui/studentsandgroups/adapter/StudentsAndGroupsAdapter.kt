package com.akmaldev.tutorapp.presentation.ui.studentsandgroups.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akmaldev.tutorapp.data.model.response.auth._auth.TutorGroup
import com.akmaldev.tutorapp.databinding.RecyclerViewGroupsItemBinding

class StudentsAndGroupsAdapter : RecyclerView.Adapter<StudentsAndGroupsAdapter.StudentsAndGroupsViewHolder>() {

    private var groups: List<TutorGroup> = emptyList()
    private var onItemClickListener: ((TutorGroup) -> Unit)? = null

    inner class StudentsAndGroupsViewHolder(private val binding: RecyclerViewGroupsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(data: TutorGroup) {
            with(binding) {
                tvGroupName.text = "${data.name}(${data.faculty})"
                tvStudentCount.text = "${data.studentCount}-student"
                root.setOnClickListener {
                    onItemClickListener?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsAndGroupsViewHolder {
        return StudentsAndGroupsViewHolder(
            RecyclerViewGroupsItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = groups.size

    override fun onBindViewHolder(holder: StudentsAndGroupsViewHolder, position: Int) {
        holder.onBind(groups[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(groups: List<TutorGroup>) {
        this.groups = groups
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(callback: (TutorGroup) -> Unit) {
        onItemClickListener = callback
    }
}