package com.akmaldev.tutorapp.presentation.ui.auth

import android.annotation.SuppressLint
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.data.model.request.auth._auth.StudentLoginRequestData
import com.akmaldev.tutorapp.data.model.request.auth._auth.TutorLoginRequestData
import com.akmaldev.tutorapp.databinding.FragmentAuthBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.auth.viewmodel.impl.AuthViewModelImpl
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.extension.formatFullName
import com.akmaldev.tutorapp.util.extension.formatName
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.toastMessage
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    @Inject
    lateinit var gson: Gson

    private val args: AuthFragmentArgs by navArgs()
    private val viewModel: AuthViewModelImpl by viewModels()

    @SuppressLint("ClickableViewAccessibility")
    override fun FragmentAuthBinding.setViews() {
        disactiveNextButton()
        when (args.userType) {
            STUDENT -> etLogin.inputType = InputType.TYPE_CLASS_NUMBER
            TUTOR -> etLogin.inputType = InputType.TYPE_CLASS_TEXT
        }
    }

    override fun FragmentAuthBinding.setListeners() {
        btnNext.setOnClickListener {
            val loginInputText = etLogin.text.toString().trim()
            val passwordInputText = etPassword.text.toString().trim()

            if (loginInputText.isBlank()) toastMessage("Login kiritng")
            else {
                if (passwordInputText.isBlank()) toastMessage("Parol kiriting")
            }
            if (loginInputText.isNotBlank() && passwordInputText.isNotBlank()) {
                when (args.userType) {
                    STUDENT -> {
                        val login = etLogin.text.toString().trim().toLong()
                        val password = etPassword.text.toString().trim()
                        val studentLoginRequestData = StudentLoginRequestData(login, password)
                        viewModel.studentLogin(studentLoginRequestData)
                    }

                    TUTOR -> {
                        val login = etLogin.text.toString().trim()
                        val password = etPassword.text.toString().trim()
                        val tutorLoginRequestData = TutorLoginRequestData(login, password)
                        viewModel.tutorLogin(tutorLoginRequestData)
                    }
                }
            }
        }
        etLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0 ?: EMPTY_STRING
                if (text.isNotBlank()) {
                    val passwordInputText = etPassword.text.toString().trim()
                    if (passwordInputText.isNotBlank()) activeNextButton()
                    else disactiveNextButton()
                } else disactiveNextButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0 ?: EMPTY_STRING
                if (text.isNotBlank()) {
                    val loginInputText = etLogin.text.toString().trim()
                    if (loginInputText.isNotBlank()) activeNextButton()
                    else disactiveNextButton()
                } else disactiveNextButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    override fun FragmentAuthBinding.setObservables() {
        with(viewModel) {
            studentFlow.onEach {
                when (it) {
                    WrappedResponse.Loading -> {
                    }

                    is WrappedResponse.Error -> {
                        toastMessage(it.message)
                    }

                    is WrappedResponse.Success -> {
                        val token = it.data.token
                        val student = it.data.student
                        sharedPreferencesHelper.studentFirstName = student.first_name.formatName()
                        sharedPreferencesHelper.studentFullName = student.full_name.formatFullName()
                        sharedPreferencesHelper.studentGroupName = student.group.name
                        sharedPreferencesHelper.studentFacultyName = student.department.name
                        sharedPreferencesHelper.studentId = student._id
                        sharedPreferencesHelper.studentRegion = student.currentProvince.name
                        sharedPreferencesHelper.studentGender = student.gender.name
                        sharedPreferencesHelper.studentImage = student.image
                        sharedPreferencesHelper.accessToken = token
                        navigateTo(R.id.action_authFragment_to_studentFragment)
                    }
                }
            }.launchIn(lifecycleScope)
            tutorFlow.onEach {
                when (it) {
                    WrappedResponse.Loading -> {
                    }

                    is WrappedResponse.Error -> {
                        toastMessage(it.message)
                    }

                    is WrappedResponse.Success -> {
                        val tutor = it.data.data
                        val token = it.data.token
                        val tutorGroups = gson.toJson(tutor.group)
                        sharedPreferencesHelper.tutorName = tutor.name
                        sharedPreferencesHelper.tutorImage = tutor.image
                        sharedPreferencesHelper.tutorPhoneNumber = tutor.phone
                        sharedPreferencesHelper.tutorGroups = tutorGroups
                        sharedPreferencesHelper.accessToken = token
                        sharedPreferencesHelper.tutorId = it.data.data._id
                        navigateTo(R.id.action_authFragment_to_tutorFragment)
                    }
                }
            }.launchIn(lifecycleScope)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun FragmentAuthBinding.activeNextButton() {
        btnNext.apply {
            btnNext.setOnTouchListener { _, _ -> false }
            alpha = 1.0f
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun FragmentAuthBinding.disactiveNextButton() {
        btnNext.apply {
            btnNext.setOnTouchListener { _, _ -> true }
            alpha = .7f
        }
    }

    companion object {
        private const val TAG = "AuthFragmentTag"

        private const val STUDENT = 0
        private const val TUTOR = 1
    }
}