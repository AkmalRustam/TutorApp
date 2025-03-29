package com.akmaldev.tutorapp.presentation.ui.tutorchangepassword

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.data.model.request.main.tutorchangepassword.TutorChangePasswordRequestData
import com.akmaldev.tutorapp.databinding.FragmentTutorChangePasswordBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.tutorchangepassword.viewmodel.impl.TutorChangePasswordViewModelImpl
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.toastMessage
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TutorChangePassword :
    BaseFragment<FragmentTutorChangePasswordBinding>(FragmentTutorChangePasswordBinding::inflate) {

    private val viewModel: TutorChangePasswordViewModelImpl by viewModels()

    override fun FragmentTutorChangePasswordBinding.setViews() {
        disactiveNextButton()
    }

    override fun FragmentTutorChangePasswordBinding.setListeners() {
        etOldPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0 ?: EMPTY_STRING
                if (text.isNotBlank()) {
                    val passwordInputText = etNewPassword.text.toString().trim()
                    if (passwordInputText.isNotBlank()) activeNextButton()
                    else disactiveNextButton()
                } else disactiveNextButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        etNewPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0 ?: EMPTY_STRING
                if (text.isNotBlank()) {
                    val loginInputText = etOldPassword.text.toString().trim()
                    if (loginInputText.isNotBlank()) activeNextButton()
                    else disactiveNextButton()
                } else disactiveNextButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        btnNext.setOnClickListener {
            val oldPasswordInputText = etOldPassword.text.toString().trim()
            val newPasswordInputText = etNewPassword.text.toString().trim()

            if (oldPasswordInputText.isBlank()) toastMessage("Eski parolni kiritng")
            else {
                if (newPasswordInputText.isBlank()) toastMessage("Yangi parolni kiriting")
            }
            if (oldPasswordInputText.isNotBlank() && newPasswordInputText.isNotBlank()) {
                val requestData = TutorChangePasswordRequestData(
                    oldPasswordInputText,
                    newPasswordInputText
                )
                viewModel.changePassword(requestData)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun FragmentTutorChangePasswordBinding.setObservables() {
        viewModel.changePasswordFlow.onEach {
            when(it) {
                WrappedResponse.Loading -> {}
                is WrappedResponse.Error -> {
                    when(it.code) {
                        in 500..599 -> {
                            toastMessage(it.message)
                        }
                        else -> {
                            tvTitle.text = "Kiritgan parol xato"
                            tvSubTitle.text = "Aziz tyutor kiritgan parollingiz xato\nyana urunib ko’ring"
                            etOldPassword.setBackgroundResource(R.drawable.change_password_input_error_background)
                        }
                    }
                }
                is WrappedResponse.Success -> {
                    navigateTo(R.id.action_tutorChangePassword_to_successfullyPasswordChangedFragment)
                }
            }
        }.launchIn(lifecycleScope)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun FragmentTutorChangePasswordBinding.activeNextButton() {
        btnNext.apply {
            btnNext.setOnTouchListener { _, _ -> false }
            alpha = 1.0f
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun FragmentTutorChangePasswordBinding.disactiveNextButton() {
        btnNext.apply {
            btnNext.setOnTouchListener { _, _ -> true }
            alpha = .7f
        }
    }
}