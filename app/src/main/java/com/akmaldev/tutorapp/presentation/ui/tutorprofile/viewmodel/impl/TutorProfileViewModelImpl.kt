package com.akmaldev.tutorapp.presentation.ui.tutorprofile.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmaldev.tutorapp.data.model.response.main.tutorprofile.TutorProfileResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorprofile.TutorUpdateProfileImageResponseData
import com.akmaldev.tutorapp.domain.usecase.main.TutorProfileUseCase
import com.akmaldev.tutorapp.domain.usecase.main.TutorUpdateProfileImageUseCase
import com.akmaldev.tutorapp.presentation.ui.tutorprofile.viewmodel.TutorProfileViewModel
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class TutorProfileViewModelImpl @Inject constructor(
    private val tutorProfileUseCase: TutorProfileUseCase,
    private val tutorUpdateProfileImageUseCase: TutorUpdateProfileImageUseCase
) : ViewModel(), TutorProfileViewModel {

    private val _profile: MutableSharedFlow<WrappedResponse<TutorProfileResponseData>> =
        MutableSharedFlow()
    override val profile: SharedFlow<WrappedResponse<TutorProfileResponseData>>
        get() = _profile

    private val _updateProfileImageFlow: MutableSharedFlow<WrappedResponse<TutorUpdateProfileImageResponseData>> =
        MutableSharedFlow()
    override val updateProfileImageFlow: SharedFlow<WrappedResponse<TutorUpdateProfileImageResponseData>>
        get() = _updateProfileImageFlow

    override fun tutorProfile() {
        viewModelScope.launch {
            val response = tutorProfileUseCase()
            _profile.emit(WrappedResponse.Loading)
            _profile.emit(response)
        }
    }

    override fun updateProfileImage(image: MultipartBody.Part) {
        viewModelScope.launch {
            val response = tutorUpdateProfileImageUseCase(image)
            _updateProfileImageFlow.emit(WrappedResponse.Loading)
            _updateProfileImageFlow.emit(response)
        }
    }
}