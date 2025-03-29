package com.akmaldev.tutorapp.domain.usecase.main

import com.akmaldev.tutorapp.data.model.response.main.notification.NotificationResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse

interface GetNotificationsUseCase {
    suspend operator fun invoke(): WrappedResponse<NotificationResponseData>
}