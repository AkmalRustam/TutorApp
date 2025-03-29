package com.akmaldev.tutorapp.presentation.ui.ninthquestion

import android.text.Editable
import android.text.TextWatcher
import com.akmaldev.tutorapp.databinding.FragmentNinthQuestionBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NinthQuestionFragment :
    BaseFragment<FragmentNinthQuestionBinding>(FragmentNinthQuestionBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onResume() {
        super.onResume()
        sharedPreferencesHelper.questionNumber = 12
    }

    override fun FragmentNinthQuestionBinding.setViews() {
        etNumber.setText(sharedPreferencesHelper.twelfthQuestionValue)
        etDescription.setText(sharedPreferencesHelper.thirteenthQuestionValue)
    }

    override fun FragmentNinthQuestionBinding.setListeners() {
        etNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString().trim()
                if (text.isNotBlank()) {
                    sharedPreferencesHelper.twelfthQuestionValue = text
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        etDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString().trim()
                if (text.isNotBlank()) {
                    sharedPreferencesHelper.thirteenthQuestionValue = text
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
}