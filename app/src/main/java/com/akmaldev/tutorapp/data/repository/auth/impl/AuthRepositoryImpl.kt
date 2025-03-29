package com.akmaldev.tutorapp.data.repository.auth.impl

import com.akmaldev.tutorapp.data.model.request.auth._auth.StudentLoginRequestData
import com.akmaldev.tutorapp.data.model.request.auth._auth.TutorLoginRequestData
import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentLoginResponseData
import com.akmaldev.tutorapp.data.model.response.auth._auth.TutorLoginResponseData
import com.akmaldev.tutorapp.data.remote.service.auth.AuthService
import com.akmaldev.tutorapp.data.repository.auth.AuthRepository
import com.akmaldev.tutorapp.util.extension.toWrappedResponse
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService) :
    AuthRepository {
    override suspend fun studentLogin(studentLoginRequestData: StudentLoginRequestData): WrappedResponse<StudentLoginResponseData> {
        return authService.studentLogin(studentLoginRequestData).toWrappedResponse()
    }

    override suspend fun tutorLogin(tutorLoginRequestData: TutorLoginRequestData): WrappedResponse<TutorLoginResponseData> {
        return authService.tutorLogin(tutorLoginRequestData).toWrappedResponse()
    }
}