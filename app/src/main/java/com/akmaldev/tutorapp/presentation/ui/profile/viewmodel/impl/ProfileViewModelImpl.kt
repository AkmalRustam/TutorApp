package com.akmaldev.tutorapp.presentation.ui.profile.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmaldev.tutorapp.data.model.response.main.profile.StudentProfileResponseData
import com.akmaldev.tutorapp.domain.usecase.main.GetStudentProfileUseCase
import com.akmaldev.tutorapp.presentation.ui.profile.viewmodel.ProfileViewModel
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val getStudentProfileUseCase: GetStudentProfileUseCase
) : ViewModel(), ProfileViewModel {

    private val _studentProfileFlow: MutableSharedFlow<WrappedResponse<StudentProfileResponseData>> =
        MutableSharedFlow()
    override val studentProfileFlow: SharedFlow<WrappedResponse<StudentProfileResponseData>>
        get() = _studentProfileFlow

    override fun getStudentProfile() {
        viewModelScope.launch {
            val response = getStudentProfileUseCase()
            _studentProfileFlow.emit(WrappedResponse.Loading)
            _studentProfileFlow.emit(response)
        }
    }
}