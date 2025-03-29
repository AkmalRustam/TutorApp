package com.akmaldev.tutorapp.presentation.ui.usertype

import androidx.activity.OnBackPressedCallback
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentUserTypeBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment

class UserTypeFragment : BaseFragment<FragmentUserTypeBinding>(FragmentUserTypeBinding::inflate) {

    private var defaultSelectedUserType = TUTOR
    private var hasClickTutorButton = true
    private var hasClickStudentButton = false

    override fun FragmentUserTypeBinding.setViews() {
        updateUIByDefaultSelectedUserType(defaultSelectedUserType)
    }

    override fun FragmentUserTypeBinding.setListeners() {
        btnStudent.setOnClickListener {
            defaultSelectedUserType = STUDENT
            updateUIByDefaultSelectedUserType(defaultSelectedUserType)
            if (hasClickStudentButton) {
                val direction = UserTypeFragmentDirections.actionUserTypeFragmentToAuthFragment(defaultSelectedUserType)
                navigateTo(direction)
            } else {
                hasClickStudentButton = true
                hasClickTutorButton = false
            }
        }
        btnTutor.setOnClickListener {
            defaultSelectedUserType = TUTOR
            updateUIByDefaultSelectedUserType(defaultSelectedUserType)
            if (hasClickTutorButton) {
                val direction = UserTypeFragmentDirections.actionUserTypeFragmentToAuthFragment(defaultSelectedUserType)
                navigateTo(direction)
            } else {
                hasClickTutorButton = true
                hasClickStudentButton = false
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
    }

    private fun FragmentUserTypeBinding.updateUIByDefaultSelectedUserType(
        defaultSelectedUserType: Int
    ) {
        when (defaultSelectedUserType) {
            STUDENT -> {
                ivUserTypeBanner.setImageResource(R.drawable.ic_student)
                btnStudent.setBackgroundResource(R.drawable.btn_tutor_background)
                tvStudent.apply {
                    setTextColor(context.getColor(R.color.white))
                }
                btnTutor.setBackgroundResource(R.drawable.btn_student_background)
                tvTutor.apply {
                    setTextColor(context.getColor(R.color.blue))
                }
            }

            TUTOR -> {
                ivUserTypeBanner.setImageResource(R.drawable.ic_tutor)
                btnStudent.setBackgroundResource(R.drawable.btn_student_background)
                tvStudent.apply {
                    setTextColor(context.getColor(R.color.blue))
                }
                btnTutor.setBackgroundResource(R.drawable.btn_tutor_background)
                tvTutor.apply {
                    setTextColor(context.getColor(R.color.white))
                }
            }
        }
    }

    companion object {
        private const val STUDENT = 0
        private const val TUTOR = 1
    }
}
