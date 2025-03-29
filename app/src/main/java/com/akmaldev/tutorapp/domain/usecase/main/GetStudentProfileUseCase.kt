package com.akmaldev.tutorapp.domain.usecase.main

import com.akmaldev.tutorapp.data.model.response.main.profile.StudentProfileResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse

interface GetStudentProfileUseCase {
    suspend operator fun invoke(): WrappedResponse<StudentProfileResponseData>
}