package com.akmaldev.tutorapp.presentation.ui.student

import android.annotation.SuppressLint
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentStudentBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.profile.viewmodel.impl.ProfileViewModelImpl
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StudentFragment : BaseFragment<FragmentStudentBinding>(FragmentStudentBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    @SuppressLint("SetTextI18n")
    override fun FragmentStudentBinding.setViews() {
        val studentFirstName = "${
            sharedPreferencesHelper.studentFirstName.take(1).uppercase()
        }${sharedPreferencesHelper.studentFirstName.drop(1).lowercase()}"
        tvUserName.apply {
            text = "Salom, $studentFirstName"
            isSelected = true
        }
        tvSubtitle.isSelected = true
        tvNumberOfQuestions.text = "${sharedPreferencesHelper.questionNumber}/17"
        when (sharedPreferencesHelper.questionNumber) {
            1 -> {
                btnStudentState.setBackgroundResource(R.drawable.btn_tutor_background)
                tvStudentState.text = getString(R.string.student_start)
            }

            17 -> {
                btnStudentState.setBackgroundResource(R.drawable.btn_student_state_finish)
                tvStudentState.text = getString(R.string.student_finish)
            }

            else -> {
                btnStudentState.setBackgroundResource(R.drawable.btn_tutor_background)
                tvStudentState.text = getString(R.string.student_next)
            }
        }
        Glide
            .with(requireContext())
            .load(sharedPreferencesHelper.studentImage)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(civUserImage)
    }

    override fun FragmentStudentBinding.setListeners() {
        ivMore.setOnClickListener {
            navigateTo(R.id.action_studentFragment_to_profileFragment)
        }
        ivProfile.setOnClickListener {
            navigateTo(R.id.action_studentFragment_to_notificationFragment)
        }
        btnStudentState.setOnClickListener {
            if (sharedPreferencesHelper.questionNumber == 17) {

            } else {
                val direction = StudentFragmentDirections.actionStudentFragmentToQuestionFragment(
                    sharedPreferencesHelper.questionNumber
                )
                navigateTo(direction)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
    }

    override fun FragmentStudentBinding.setValues() {
        sharedPreferencesHelper.tutorOrStudent = 1
    }

    companion object {
        private const val TAG = "StudentFragmentTag"
    }
}