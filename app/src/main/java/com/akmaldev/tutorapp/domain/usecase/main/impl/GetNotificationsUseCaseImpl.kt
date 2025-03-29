package com.akmaldev.tutorapp.domain.usecase.main.impl

import com.akmaldev.tutorapp.data.model.response.main.notification.NotificationResponseData
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.domain.usecase.main.GetNotificationsUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import javax.inject.Inject

class GetNotificationsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetNotificationsUseCase {
    override suspend fun invoke(): WrappedResponse<NotificationResponseData> =
        mainRepository.getNotifications()
}