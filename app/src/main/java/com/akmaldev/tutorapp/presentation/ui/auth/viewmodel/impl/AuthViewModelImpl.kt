package com.akmaldev.tutorapp.presentation.ui.auth.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmaldev.tutorapp.data.model.request.auth._auth.StudentLoginRequestData
import com.akmaldev.tutorapp.data.model.request.auth._auth.TutorLoginRequestData
import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentLoginResponseData
import com.akmaldev.tutorapp.data.model.response.auth._auth.TutorLoginResponseData
import com.akmaldev.tutorapp.domain.usecase.auth.GetStudentLoginUseCase
import com.akmaldev.tutorapp.domain.usecase.auth.GetTutorLoginUseCase
import com.akmaldev.tutorapp.presentation.ui.auth.viewmodel.AuthViewModel
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModelImpl @Inject constructor(
    private val getStudentLoginUseCase: GetStudentLoginUseCase,
    private val getTutorLoginUseCase: GetTutorLoginUseCase
) : ViewModel(), AuthViewModel {

    private val _studentFlow: MutableSharedFlow<WrappedResponse<StudentLoginResponseData>> =
        MutableSharedFlow()
    override val studentFlow: SharedFlow<WrappedResponse<StudentLoginResponseData>> get() = _studentFlow

    private val _tutorFlow: MutableSharedFlow<WrappedResponse<TutorLoginResponseData>> =
        MutableSharedFlow()
    override val tutorFlow: SharedFlow<WrappedResponse<TutorLoginResponseData>> get() = _tutorFlow

    override fun studentLogin(studentLoginRequestData: StudentLoginRequestData) {
        viewModelScope.launch {
            val response = getStudentLoginUseCase(studentLoginRequestData)
            _studentFlow.emit(WrappedResponse.Loading)
            _studentFlow.emit(response)

        }
    }

    override fun tutorLogin(tutorLoginRequestData: TutorLoginRequestData) {
        viewModelScope.launch {
            val response = getTutorLoginUseCase(tutorLoginRequestData)
            _tutorFlow.emit(WrappedResponse.Loading)
            _tutorFlow.emit(response)
        }
    }
}