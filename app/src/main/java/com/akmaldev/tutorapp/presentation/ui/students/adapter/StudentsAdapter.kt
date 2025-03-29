package com.akmaldev.tutorapp.presentation.ui.students.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.data.model.response.main.students.StudentsData
import com.akmaldev.tutorapp.databinding.RecyclerViewStudentsItemBinding

class StudentsAdapter :
    PagingDataAdapter<StudentsData, StudentsAdapter.StudentViewHolder>(StudentDiffCallback()) {

    private var onItemClickListener: ((StudentsData) -> Unit)? = null
    private var onStatusClickListener: ((StudentsData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            RecyclerViewStudentsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        getItem(position)?.let { student ->
            holder.bind(student)
        }
    }

    inner class StudentViewHolder(private val binding: RecyclerViewStudentsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(student: StudentsData) {
            with(binding) {
                when (student.status) {
                    GREEN -> ivStudentStatus.backgroundTintList =
                        ivStudentStatus.context.getColorStateList(R.color.green)

                    YELLOW -> ivStudentStatus.backgroundTintList =
                        ivStudentStatus.context.getColorStateList(R.color.orange)

                    RED -> ivStudentStatus.backgroundTintList =
                        ivStudentStatus.context.getColorStateList(R.color.red)

                    BLUE -> ivStudentStatus.backgroundTintList =
                        ivStudentStatus.context.getColorStateList(R.color.blue)
                }
                tvStudentsName.text = "${student.first_name.take(1).uppercase()}${
                    student.first_name.drop(1).lowercase()
                } ${student.second_name.take(1).uppercase()}${
                    student.second_name.drop(1).lowercase()
                }"
                tvStudentGroup.text = student.group.name

                ivStudentStatus.setOnClickListener {
                    onStatusClickListener?.invoke(student)
                }
                root.setOnClickListener {
                    onItemClickListener?.invoke(student)
                }
            }
        }
    }

    class StudentDiffCallback : DiffUtil.ItemCallback<StudentsData>() {
        override fun areItemsTheSame(oldItem: StudentsData, newItem: StudentsData): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: StudentsData, newItem: StudentsData): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(callback: (StudentsData) -> Unit) {
        onItemClickListener = callback
    }

    fun setOnStatusClickListener(callback: (StudentsData) -> Unit) {
        onStatusClickListener = callback
    }

    companion object {
        private const val GREEN = "green"
        private const val YELLOW = "yellow"
        private const val RED = "red"
        private const val BLUE = "blue"
    }
}