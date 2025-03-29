package com.akmaldev.tutorapp.presentation.ui.firstquestion

import android.text.Editable
import android.text.TextWatcher
import com.akmaldev.tutorapp.databinding.FragmentFirstQuestionBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.helper.PhoneNumberTextWatcher
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FirstQuestionFragment :
    BaseFragment<FragmentFirstQuestionBinding>(FragmentFirstQuestionBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onResume() {
        super.onResume()
        sharedPreferencesHelper.questionNumber = 1
    }

    override fun FragmentFirstQuestionBinding.setViews() {
        etPhoneNumber.apply {
            setText(sharedPreferencesHelper.firstQuestionValue)
            addTextChangedListener(PhoneNumberTextWatcher())
        }
    }

    override fun FragmentFirstQuestionBinding.setListeners() {
        etPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString().trim()
                if (text.isNotBlank()) {
                    sharedPreferencesHelper.firstQuestionValue = text
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
}