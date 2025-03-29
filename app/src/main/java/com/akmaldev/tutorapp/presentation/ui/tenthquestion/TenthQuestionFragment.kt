package com.akmaldev.tutorapp.presentation.ui.tenthquestion

import android.view.View
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentTenthQuestionBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.question.QuestionFragmentDirections
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TenthQuestionFragment :
    BaseFragment<FragmentTenthQuestionBinding>(FragmentTenthQuestionBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onResume() {
        super.onResume()
        sharedPreferencesHelper.questionNumber = 14
    }

    override fun FragmentTenthQuestionBinding.setViews() {
        if (sharedPreferencesHelper.fourteenthQuestionValue.isNotBlank()) {
            btnHeatingEquipment.apply {
                setBackgroundResource(R.drawable.take_picture_container_background)
                isClickable = false
                isFocusable = false
                isEnabled = false
            }
            ivCameraHeatingEquipment.isVisible = false
            tvTakePictureHeatingEquipment.apply {
                text = "Rasmga o’lindi"
                setTextColor(context.getColor(R.color.green))
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }
        }
        if (sharedPreferencesHelper.fifteenthQuestionValue.isNotBlank()) {
            btnGasPlate.apply {
                setBackgroundResource(R.drawable.take_picture_container_background)
                isClickable = false
                isFocusable = false
                isEnabled = false
            }
            ivCameraGasPlate.isVisible = false
            tvTakePictureGasPlate.apply {
                text = "Rasmga o’lindi"
                setTextColor(context.getColor(R.color.green))
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }
        }
    }

    override fun FragmentTenthQuestionBinding.setListeners() {
        btnGasPlate.setOnClickListener {
            val direction = QuestionFragmentDirections.actionQuestionFragmentToCameraFragment(
                false,
                false,
                false,
                false,
                false
            )
            navigateTo(direction)
        }
        btnHeatingEquipment.setOnClickListener {
            val direction = QuestionFragmentDirections.actionQuestionFragmentToCameraFragment(
                true,
                false,
                false,
                false,
                false
            )
            navigateTo(direction)
        }
    }
}