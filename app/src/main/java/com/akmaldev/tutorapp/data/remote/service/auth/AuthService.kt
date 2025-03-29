package com.akmaldev.tutorapp.data.remote.service.auth

import com.akmaldev.tutorapp.data.model.request.auth._auth.StudentLoginRequestData
import com.akmaldev.tutorapp.data.model.request.auth._auth.TutorLoginRequestData
import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentLoginResponseData
import com.akmaldev.tutorapp.data.model.response.auth._auth.TutorLoginResponseData
import com.akmaldev.tutorapp.util.constant.ServiceConstants.STUDENT_SIGN
import com.akmaldev.tutorapp.util.constant.ServiceConstants.TUTOR_LOGIN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST(STUDENT_SIGN)
    suspend fun studentLogin(@Body studentLoginRequestData: StudentLoginRequestData): Response<StudentLoginResponseData>

    @POST(TUTOR_LOGIN)
    suspend fun tutorLogin(@Body tutorLoginRequestData: TutorLoginRequestData): Response<TutorLoginResponseData>
}