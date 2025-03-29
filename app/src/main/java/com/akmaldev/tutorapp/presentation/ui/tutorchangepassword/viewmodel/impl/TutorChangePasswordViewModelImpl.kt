package com.akmaldev.tutorapp.presentation.ui.tutorchangepassword.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmaldev.tutorapp.data.model.request.main.tutorchangepassword.TutorChangePasswordRequestData
import com.akmaldev.tutorapp.data.model.response.main.tutorchangepassword.TutorChangePasswordResponseData
import com.akmaldev.tutorapp.domain.usecase.main.ChangePasswordUseCase
import com.akmaldev.tutorapp.presentation.ui.tutorchangepassword.viewmodel.TutorChangePasswordViewModel
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorChangePasswordViewModelImpl @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase
) : ViewModel(), TutorChangePasswordViewModel {

    private val _changePasswordFlow: MutableSharedFlow<WrappedResponse<TutorChangePasswordResponseData>> =
        MutableSharedFlow()
    override val changePasswordFlow: SharedFlow<WrappedResponse<TutorChangePasswordResponseData>>
        get() = _changePasswordFlow

    override fun changePassword(requestData: TutorChangePasswordRequestData) {
        viewModelScope.launch {
            val response = changePasswordUseCase(requestData)
            _changePasswordFlow.emit(WrappedResponse.Loading)
            _changePasswordFlow.emit(response)
        }
    }
}