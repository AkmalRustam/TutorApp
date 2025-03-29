package com.akmaldev.tutorapp.domain.usecase.main

import com.akmaldev.tutorapp.data.model.response.main.tutornotification.TutorNotificationsResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse

interface GetTutorNotificationsUseCase {
    suspend operator fun invoke(tutorId: String): WrappedResponse<TutorNotificationsResponseData>
}