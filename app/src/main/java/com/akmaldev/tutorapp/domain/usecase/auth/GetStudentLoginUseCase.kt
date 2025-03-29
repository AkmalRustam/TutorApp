package com.akmaldev.tutorapp.domain.usecase.auth

import com.akmaldev.tutorapp.data.model.request.auth._auth.StudentLoginRequestData
import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentLoginResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse

interface GetStudentLoginUseCase {
    suspend operator fun invoke(studentLoginRequestData: StudentLoginRequestData): WrappedResponse<StudentLoginResponseData>
}