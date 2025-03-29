package com.akmaldev.tutorapp.presentation.ui.studenttype

import com.akmaldev.tutorapp.databinding.FragmentStudentTypeBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.popBackStack

class StudentTypeFragment :
    BaseFragment<FragmentStudentTypeBinding>(FragmentStudentTypeBinding::inflate) {
    override fun FragmentStudentTypeBinding.setListeners() {
        ivBack.setOnClickListener {
            popBackStack()
        }
        greenContainer.setOnClickListener {
            val direction =
                StudentTypeFragmentDirections.actionStudentTypeFragmentToStudentStatusFragment(GREEN)
            navigateTo(direction)
        }
        yellowContainer.setOnClickListener {
            val direction =
                StudentTypeFragmentDirections.actionStudentTypeFragmentToStudentStatusFragment(
                    YELLOW
                )
            navigateTo(direction)
        }
        redContainer.setOnClickListener {
            val direction =
                StudentTypeFragmentDirections.actionStudentTypeFragmentToStudentStatusFragment(RED)
            navigateTo(direction)
        }
        blueContainer.setOnClickListener {
            val direction =
                StudentTypeFragmentDirections.actionStudentTypeFragmentToStudentStatusFragment(BLUE)
            navigateTo(direction)
        }
    }

    companion object {
        private const val GREEN = "green"
        private const val YELLOW = "yellow"
        private const val RED = "red"
        private const val BLUE = "blue"
    }
}