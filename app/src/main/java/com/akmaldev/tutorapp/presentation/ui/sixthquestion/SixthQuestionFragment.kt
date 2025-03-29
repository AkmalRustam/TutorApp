package com.akmaldev.tutorapp.presentation.ui.sixthquestion

import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import com.akmaldev.tutorapp.databinding.FragmentSixthQuestionBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.google.android.material.radiobutton.MaterialRadioButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SixthQuestionFragment :
    BaseFragment<FragmentSixthQuestionBinding>(FragmentSixthQuestionBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onResume() {
        super.onResume()
        sharedPreferencesHelper.questionNumber = 7
    }

    override fun FragmentSixthQuestionBinding.setViews() {
        rgState.check(sharedPreferencesHelper.seventhQuestionRadioButtonId)
        etAddress.apply {
            isVisible = rgState.checkedRadioButtonId == rbEighth.id
            setText(sharedPreferencesHelper.seventhQuestionValue)
        }
    }

    override fun FragmentSixthQuestionBinding.setListeners() {
        rgState.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = binding.root.findViewById<MaterialRadioButton>(checkedId)
            val selectedText = selectedRadioButton.text.toString().trim()
            sharedPreferencesHelper.seventhQuestionValue = selectedText
            sharedPreferencesHelper.seventhQuestionRadioButtonId = selectedRadioButton.id
            etAddress.isVisible = checkedId == rbEighth.id
            if (checkedId == rbEighth.id) {
                etAddress.setText(EMPTY_STRING)
                sharedPreferencesHelper.seventhQuestionValue = EMPTY_STRING
            }
        }
        etAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString().trim()
                if (text.isNotBlank()) {
                    sharedPreferencesHelper.seventhQuestionValue = text
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
}