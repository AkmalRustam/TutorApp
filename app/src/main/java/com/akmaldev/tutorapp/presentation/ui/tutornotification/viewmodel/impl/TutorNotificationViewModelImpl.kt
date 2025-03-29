package com.akmaldev.tutorapp.presentation.ui.tutornotification.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmaldev.tutorapp.data.model.response.main.tutornotification.TutorNotificationsResponseData
import com.akmaldev.tutorapp.domain.usecase.main.GetTutorNotificationsUseCase
import com.akmaldev.tutorapp.presentation.ui.tutornotification.viewmodel.TutorNotificationViewModel
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorNotificationViewModelImpl @Inject constructor(
    private val getTutorNotificationsUseCase: GetTutorNotificationsUseCase
) : ViewModel(), TutorNotificationViewModel {

    private val _notificationsFlow: MutableSharedFlow<WrappedResponse<TutorNotificationsResponseData>> =
        MutableSharedFlow()
    override val notificationsFlow: SharedFlow<WrappedResponse<TutorNotificationsResponseData>>
        get() = _notificationsFlow

    override fun getTutorNotifications(tutorId: String) {
        viewModelScope.launch {
            val response = getTutorNotificationsUseCase(tutorId)
            _notificationsFlow.emit(WrappedResponse.Loading)
            _notificationsFlow.emit(response)
        }
    }
}