package com.akmaldev.tutorapp.domain.usecase.auth.impl

import com.akmaldev.tutorapp.data.model.request.auth._auth.TutorLoginRequestData
import com.akmaldev.tutorapp.data.model.response.auth._auth.TutorLoginResponseData
import com.akmaldev.tutorapp.data.repository.auth.AuthRepository
import com.akmaldev.tutorapp.domain.usecase.auth.GetTutorLoginUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import javax.inject.Inject

class GetTutorLoginUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    GetTutorLoginUseCase {
    override suspend fun invoke(tutorLoginRequestData: TutorLoginRequestData): WrappedResponse<TutorLoginResponseData> =
        authRepository.tutorLogin(tutorLoginRequestData)
}