package com.akmaldev.tutorapp.presentation.ui.secondquestion

import com.akmaldev.tutorapp.databinding.FragmentSecondQuestionBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.google.android.material.radiobutton.MaterialRadioButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondQuestionFragment :
    BaseFragment<FragmentSecondQuestionBinding>(FragmentSecondQuestionBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onResume() {
        super.onResume()
        sharedPreferencesHelper.questionNumber = 2
    }

    override fun FragmentSecondQuestionBinding.setViews() {
        rgAddress.check(sharedPreferencesHelper.secondQuestionRadioButtonId)
    }

    override fun FragmentSecondQuestionBinding.setListeners() {
        rgAddress.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = binding.root.findViewById<MaterialRadioButton>(checkedId)
            val selectedText = selectedRadioButton.text.toString().trim()
            sharedPreferencesHelper.secondQuestionValue = selectedText
            sharedPreferencesHelper.secondQuestionRadioButtonId = selectedRadioButton.id
        }
    }
}