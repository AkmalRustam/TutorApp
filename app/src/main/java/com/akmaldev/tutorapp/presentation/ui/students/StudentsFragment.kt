package com.akmaldev.tutorapp.presentation.ui.students

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentStudentsBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.students.adapter.StudentsAdapter
import com.akmaldev.tutorapp.presentation.ui.students.viewmodel.impl.StudentsViewModelImpl
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.extension.toastMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentsFragment : BaseFragment<FragmentStudentsBinding>(FragmentStudentsBinding::inflate) {

    private val args: StudentsFragmentArgs by navArgs()
    private val viewModel: StudentsViewModelImpl by viewModels()
    private val studentsAdapter = StudentsAdapter()

    override fun FragmentStudentsBinding.setViews() {
        tvGroupName.text = "${args.groupName}(${args.faculty})"
        recyclerViewGroups.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = studentsAdapter
        }
    }

    override fun FragmentStudentsBinding.setObservables() {
        lifecycleScope.launch {
            viewModel.getStudents(args.groupName).collectLatest {
                studentsAdapter.submitData(it)
            }
        }
    }

    override fun FragmentStudentsBinding.setListeners() {
        ivBack.setOnClickListener {
            popBackStack()
        }
        studentsAdapter.apply {
            setOnItemClickListener {
                val image = it.image
                val name = it.first_name.take(1).uppercase() + it.first_name.drop(1).lowercase()
                val surname = it.second_name.take(1).uppercase() + it.second_name.drop(1).lowercase()
                val group = it.group.name
                val faculty = args.faculty
                val region = it.province.name
                val gender = it.gender
                val lat = it.location?.lat ?: EMPTY_STRING
                val lon = it.location?.long ?: EMPTY_STRING
                val status = it.status
                val direction =
                    StudentsFragmentDirections.actionStudentsFragmentToTutorStudentProfileFragment(
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
                if (it.hasFormFilled) {
                    val studentFullName = "${it.first_name.take(1).uppercase()}${
                        it.first_name.drop(1).lowercase()
                    } ${it.second_name.take(1).uppercase()}${
                        it.second_name.drop(1).lowercase()
                    }"
                    val studentId = it._id
                    val studentStatus = it.status
                    val direction = StudentsFragmentDirections.actionStudentsFragmentToTutorStudentStatusFragment(studentId, studentFullName, studentStatus)
                    navigateTo(direction)
                } else {
                    toastMessage("Bu student ma'lumotlari mavjud emas")
                }
            }
        }
    }

    companion object {
        private const val TAG = "StudentsFragmentTag"
    }
}