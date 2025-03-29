package com.akmaldev.tutorapp.presentation.ui.eighthquestion

import android.text.Editable
import android.text.TextWatcher
import com.akmaldev.tutorapp.databinding.FragmentEighthQuestionBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.helper.PhoneNumberTextWatcher
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EighthQuestionFragment :
    BaseFragment<FragmentEighthQuestionBinding>(FragmentEighthQuestionBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onResume() {
        super.onResume()
        sharedPreferencesHelper.questionNumber = 10
    }

    override fun FragmentEighthQuestionBinding.setViews() {
        etFio.setText(sharedPreferencesHelper.tenthQuestionValue)
        etPhoneNumber.addTextChangedListener(PhoneNumberTextWatcher())
        etPhoneNumber.setText(sharedPreferencesHelper.eleventhQuestionValue)
    }

    override fun FragmentEighthQuestionBinding.setListeners() {
        etFio.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString().trim()
                if (text.isNotBlank()) {
                    sharedPreferencesHelper.tenthQuestionValue = text
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        etPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString().trim()
                if (text.isNotBlank()) {
                    sharedPreferencesHelper.eleventhQuestionValue = text
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
}