package com.akmaldev.tutorapp.presentation.ui.tutornotification.viewmodel

import com.akmaldev.tutorapp.data.model.response.main.tutornotification.TutorNotificationsResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import kotlinx.coroutines.flow.SharedFlow

interface TutorNotificationViewModel {
    val notificationsFlow: SharedFlow<WrappedResponse<TutorNotificationsResponseData>>
    fun getTutorNotifications(tutorId: String)
}