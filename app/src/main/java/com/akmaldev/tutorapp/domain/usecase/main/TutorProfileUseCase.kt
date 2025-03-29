package com.akmaldev.tutorapp.domain.usecase.main

import com.akmaldev.tutorapp.data.model.response.main.tutorprofile.TutorProfileResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse

interface TutorProfileUseCase {
    suspend operator fun invoke(): WrappedResponse<TutorProfileResponseData>
}