package com.akmaldev.tutorapp.domain.usecase.main.impl

import com.akmaldev.tutorapp.data.model.response.main.tutornotification.TutorNotificationsResponseData
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.domain.usecase.main.GetTutorNotificationsUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import javax.inject.Inject

class GetTutorNotificationsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetTutorNotificationsUseCase {
    override suspend fun invoke(tutorId: String): WrappedResponse<TutorNotificationsResponseData> = mainRepository.getTutorNotifications(tutorId)
}