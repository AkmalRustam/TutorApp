package com.akmaldev.tutorapp.domain.usecase.main.impl

import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.PushNotificationRequestData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.PushNotificationResponseData
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.domain.usecase.main.PushNotificationUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import javax.inject.Inject

class PushNotificationUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    PushNotificationUseCase {
    override suspend fun invoke(requestData: PushNotificationRequestData): WrappedResponse<PushNotificationResponseData> =
        mainRepository.pushNotification(requestData)
}