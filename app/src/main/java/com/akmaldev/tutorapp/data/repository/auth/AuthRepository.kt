package com.akmaldev.tutorapp.data.repository.auth

import com.akmaldev.tutorapp.data.model.request.auth._auth.StudentLoginRequestData
import com.akmaldev.tutorapp.data.model.request.auth._auth.TutorLoginRequestData
import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentLoginResponseData
import com.akmaldev.tutorapp.data.model.response.auth._auth.TutorLoginResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse

interface AuthRepository {
    suspend fun studentLogin(studentLoginRequestData: StudentLoginRequestData): WrappedResponse<StudentLoginResponseData>
    suspend fun tutorLogin(tutorLoginRequestData: TutorLoginRequestData): WrappedResponse<TutorLoginResponseData>
}