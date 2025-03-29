package com.akmaldev.tutorapp.domain.usecase.auth

import com.akmaldev.tutorapp.data.model.request.auth._auth.TutorLoginRequestData
import com.akmaldev.tutorapp.data.model.response.auth._auth.TutorLoginResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse

interface GetTutorLoginUseCase {
    suspend operator fun invoke(tutorLoginRequestData: TutorLoginRequestData): WrappedResponse<TutorLoginResponseData>
}