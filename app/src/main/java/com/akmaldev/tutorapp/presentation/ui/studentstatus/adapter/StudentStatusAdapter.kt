package com.akmaldev.tutorapp.presentation.ui.studentstatus.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.data.model.response.main.studentstatus.Data
import com.akmaldev.tutorapp.databinding.RecyclerViewStudentsItemBinding
import com.akmaldev.tutorapp.util.extension.formatFullName

class StudentStatusAdapter :
    PagingDataAdapter<Data, StudentStatusAdapter.StudentStatusViewHolder>(StudentDiffCallback()) {

    private var onItemClickListener: ((Data) -> Unit)? = null
    private var onStatusClickListener: ((Data) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentStatusViewHolder {
        return StudentStatusViewHolder(
            RecyclerViewStudentsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StudentStatusViewHolder, position: Int) {
        getItem(position)?.let { student ->
            holder.bind(student)
        }
    }

    inner class StudentStatusViewHolder(private val binding: RecyclerViewStudentsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(student: Data) {
            with(binding) {
                when (student.appartment.status) {
                    GREEN -> ivStudentStatus.backgroundTintList =
                        ivStudentStatus.context.getColorStateList(R.color.green)

                    YELLOW -> ivStudentStatus.backgroundTintList =
                        ivStudentStatus.context.getColorStateList(R.color.orange)

                    RED -> ivStudentStatus.backgroundTintList =
                        ivStudentStatus.context.getColorStateList(R.color.red)

                    else -> ivStudentStatus.backgroundTintList =
                        ivStudentStatus.context.getColorStateList(R.color.blue)
                }
                tvStudentsName.text = "${
                    student.student.full_name.formatFullName().split(' ')[1]
                } ${student.student.full_name.formatFullName().split(' ')[0]}"
                tvStudentGroup.text = student.student.group.name

                ivStudentStatus.setOnClickListener {
                    onStatusClickListener?.invoke(student)
                }
                root.setOnClickListener {
                    onItemClickListener?.invoke(student)
                }
            }
        }
    }

    class StudentDiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.appartment.studentId == newItem.appartment.studentId
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(callback: (Data) -> Unit) {
        onItemClickListener = callback
    }

    fun setOnStatusClickListener(callback: (Data) -> Unit) {
        onStatusClickListener = callback
    }

    private fun String.formatName(): String {
        val parts = this.split(" ").filter { it.isNotBlank() }
        return if (parts.size >= 2) {
            "${parts[1].capitalize()} ${parts[0].capitalize()}"
        } else {
            this.capitalize()
        }
    }

    companion object {
        private const val GREEN = "green"
        private const val YELLOW = "yellow"
        private const val RED = "red"
        private const val BLUE = "blue"
    }
}