package com.akmaldev.tutorapp.presentation.ui.thirdquestion

import android.text.Editable
import android.text.TextWatcher
import com.akmaldev.tutorapp.databinding.FragmentThirdQuestionBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ThirdQuestionFragment :
    BaseFragment<FragmentThirdQuestionBinding>(FragmentThirdQuestionBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun FragmentThirdQuestionBinding.setViews() {
        etAddress.setText(sharedPreferencesHelper.thirdQuestionValue)
    }

    override fun FragmentThirdQuestionBinding.setListeners() {
        etAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString().trim()
                if (text.isNotBlank()) {
                    sharedPreferencesHelper.thirdQuestionValue = text
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    override fun onResume() {
        super.onResume()
        sharedPreferencesHelper.questionNumber = 3
    }
}