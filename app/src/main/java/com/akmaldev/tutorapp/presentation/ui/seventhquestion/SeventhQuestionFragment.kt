package com.akmaldev.tutorapp.presentation.ui.seventhquestion

import android.text.Editable
import android.text.TextWatcher
import com.akmaldev.tutorapp.databinding.FragmentSeventhQuestionBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SeventhQuestionFragment :
    BaseFragment<FragmentSeventhQuestionBinding>(FragmentSeventhQuestionBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onResume() {
        super.onResume()
        sharedPreferencesHelper.questionNumber = 8
    }

    override fun FragmentSeventhQuestionBinding.setViews() {
        etPrice.setText(sharedPreferencesHelper.eighthQuestionValue)
        etMemberCount.setText(sharedPreferencesHelper.ninthQuestionValue)
    }

    override fun FragmentSeventhQuestionBinding.setListeners() {
        etPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString().trim()
                if (text.isNotBlank()) {
                    sharedPreferencesHelper.eighthQuestionValue = text
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        etMemberCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString().trim()
                if (text.isNotBlank()) {
                    sharedPreferencesHelper.ninthQuestionValue = text
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
}