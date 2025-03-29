package com.akmaldev.tutorapp.presentation.ui.studentstatus

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentStudentStatusBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.studentstatus.adapter.StudentStatusAdapter
import com.akmaldev.tutorapp.presentation.ui.studentstatus.viewmodel.impl.StudentStatusViewModelImpl
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.extension.formatFullName
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.popBackStack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class StudentStatusFragment :
    BaseFragment<FragmentStudentStatusBinding>(FragmentStudentStatusBinding::inflate) {

    private val args: StudentStatusFragmentArgs by navArgs()
    private val viewModel: StudentStatusViewModelImpl by viewModels()

    private var studentStatusAdapter = StudentStatusAdapter()

    override fun FragmentStudentStatusBinding.setObservables() {
        viewModel.getStudents(args.status).onEach {
            studentStatusAdapter.submitData(it)
        }.launchIn(lifecycleScope)
    }

    @SuppressLint("SetTextI18n")
    override fun FragmentStudentStatusBinding.setViews() {
        when (args.status) {
            GREEN -> tvTitle.apply {
                text = "Yashil"
                backgroundTintList = context.getColorStateList(R.color.green)
            }

            YELLOW -> tvTitle.apply {
                text = "Sariq"
                backgroundTintList = context.getColorStateList(R.color.orange)
            }

            RED -> tvTitle.apply {
                text = "Qizil"
                backgroundTintList = context.getColorStateList(R.color.red)
            }

            BLUE -> tvTitle.apply {
                text = "Yangilar"
                backgroundTintList = context.getColorStateList(R.color.blue)
            }
        }
        studentStatusAdapter = StudentStatusAdapter()
        recyclerViewStudents.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = studentStatusAdapter
        }
    }

    override fun FragmentStudentStatusBinding.setListeners() {
        ivBack.setOnClickListener {
            popBackStack()
        }
        with(studentStatusAdapter) {
            setOnItemClickListener {
                val image = it.student.image
                val name = it.student.full_name.formatName().split(" ")[0]
                val surname = it.student.full_name.formatName().split(" ")[1]
                val group = it.student.group.name
                val faculty = ""
                val region = it.student.province.name
                val gender = it.student.gender
                val lat = it.appartment.location?.lat ?: EMPTY_STRING
                val lon = it.appartment.location?.long ?: EMPTY_STRING
                val status = it.appartment.status
                val direction =
                    StudentStatusFragmentDirections.actionStudentStatusFragmentToTutorStudentProfileFragment(
                        image,
                        name,
                        surname,
                        group,
                        faculty,
                        region,
                        gender.name,
                        lat,
                        lon,
                        status
                    )
                navigateTo(direction)
            }
            setOnStatusClickListener {
                val direction =
                    StudentStatusFragmentDirections.actionStudentStatusFragmentToTutorStudentStatusFragment(
                        it.appartment.studentId,
                        "${
                            it.student.full_name.formatFullName().split(' ')[1]
                        } ${it.student.full_name.formatFullName().split(' ')[0]}",
                        it.appartment.status
                    )
                navigateTo(direction)
            }
        }
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