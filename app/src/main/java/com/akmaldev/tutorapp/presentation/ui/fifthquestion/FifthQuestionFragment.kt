package com.akmaldev.tutorapp.presentation.ui.fifthquestion

import androidx.core.view.isVisible
import com.akmaldev.tutorapp.databinding.FragmentFifthQuestionBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.google.android.material.radiobutton.MaterialRadioButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FifthQuestionFragment :
    BaseFragment<FragmentFifthQuestionBinding>(FragmentFifthQuestionBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onResume() {
        super.onResume()
        sharedPreferencesHelper.questionNumber = 5
    }

    override fun FragmentFifthQuestionBinding.setViews() {
        rgTemporary.check(sharedPreferencesHelper.fifthQuestionRadioButtonId)
        rgRentalAgreement.check(sharedPreferencesHelper.sixthQuestionRadioButtonId)
    }

    override fun FragmentFifthQuestionBinding.setListeners() {
        rgTemporary.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = binding.root.findViewById<MaterialRadioButton>(checkedId)
            val selectedText = selectedRadioButton.text.toString().trim()
            sharedPreferencesHelper.fifthQuestionValue = selectedText
            sharedPreferencesHelper.fifthQuestionRadioButtonId = selectedRadioButton.id
        }
        rgRentalAgreement.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = binding.root.findViewById<MaterialRadioButton>(checkedId)
            val selectedText = selectedRadioButton.text.toString().trim()
            sharedPreferencesHelper.sixthQuestionValue = selectedText
            sharedPreferencesHelper.sixthQuestionRadioButtonId = selectedRadioButton.id
        }
    }
}