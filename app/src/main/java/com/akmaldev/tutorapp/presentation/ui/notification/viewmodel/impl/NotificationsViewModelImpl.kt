package com.akmaldev.tutorapp.presentation.ui.notification.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmaldev.tutorapp.data.model.response.main.notification.NotificationResponseData
import com.akmaldev.tutorapp.domain.usecase.main.GetNotificationsUseCase
import com.akmaldev.tutorapp.presentation.ui.notification.viewmodel.NotificationsViewModel
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModelImpl @Inject constructor(
    private val getNotificationsUseCase: GetNotificationsUseCase
): ViewModel(), NotificationsViewModel {

    private val _notificationFlow: MutableSharedFlow<WrappedResponse<NotificationResponseData>> = MutableSharedFlow()
    override val notificationFlow: SharedFlow<WrappedResponse<NotificationResponseData>>
        get() = _notificationFlow

    override fun getNotifications() {
        viewModelScope.launch {
            val response = getNotificationsUseCase()
            _notificationFlow.emit(WrappedResponse.Loading)
            _notificationFlow.emit(response)
        }
    }
}