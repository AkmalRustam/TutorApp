package com.akmaldev.tutorapp.domain.usecase.auth.impl

import com.akmaldev.tutorapp.data.model.request.auth._auth.StudentLoginRequestData
import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentLoginResponseData
import com.akmaldev.tutorapp.data.repository.auth.AuthRepository
import com.akmaldev.tutorapp.domain.usecase.auth.GetStudentLoginUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import javax.inject.Inject

class GetStudentLoginUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    GetStudentLoginUseCase {
    override suspend fun invoke(studentLoginRequestData: StudentLoginRequestData): WrappedResponse<StudentLoginResponseData> =
        authRepository.studentLogin(studentLoginRequestData)
}