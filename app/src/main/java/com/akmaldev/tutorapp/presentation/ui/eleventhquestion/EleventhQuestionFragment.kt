package com.akmaldev.tutorapp.presentation.ui.eleventhquestion

import android.view.View
import androidx.core.view.isVisible
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentEleventhQuestionBinding
import com.akmaldev.tutorapp.databinding.FragmentTenthQuestionBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.question.QuestionFragmentDirections
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EleventhQuestionFragment :
    BaseFragment<FragmentEleventhQuestionBinding>(FragmentEleventhQuestionBinding::inflate) {
    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onResume() {
        super.onResume()
        sharedPreferencesHelper.questionNumber = 16
    }

    override fun FragmentEleventhQuestionBinding.setViews() {
        if (sharedPreferencesHelper.sixteenthQuestionValue.isNotBlank()) {
            btnChimney.apply {
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
        if (sharedPreferencesHelper.seventeenthQuestionValue.isNotBlank()) {
            btnAddition.apply {
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

    override fun FragmentEleventhQuestionBinding.setListeners() {
        btnChimney.setOnClickListener {
            val direction = QuestionFragmentDirections.actionQuestionFragmentToCameraFragment(
                false,
                false,
                false,
                true,
                false
            )
            navigateTo(direction)
        }
        btnAddition.setOnClickListener {
            val direction = QuestionFragmentDirections.actionQuestionFragmentToCameraFragment(
                false,
                false,
                false,
                false,
                true
            )
            navigateTo(direction)
        }
    }
}