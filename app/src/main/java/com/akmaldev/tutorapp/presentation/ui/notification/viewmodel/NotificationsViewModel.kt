package com.akmaldev.tutorapp.presentation.ui.notification.viewmodel

import com.akmaldev.tutorapp.data.model.response.main.notification.NotificationResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import kotlinx.coroutines.flow.SharedFlow

interface NotificationsViewModel {
    val notificationFlow: SharedFlow<WrappedResponse<NotificationResponseData>>

    fun getNotifications()
}