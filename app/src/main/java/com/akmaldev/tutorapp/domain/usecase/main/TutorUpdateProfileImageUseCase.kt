package com.akmaldev.tutorapp.domain.usecase.main

import com.akmaldev.tutorapp.data.model.response.main.tutorprofile.TutorUpdateProfileImageResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import okhttp3.MultipartBody

interface TutorUpdateProfileImageUseCase {
    suspend operator fun invoke(image: MultipartBody.Part): WrappedResponse<TutorUpdateProfileImageResponseData>
}