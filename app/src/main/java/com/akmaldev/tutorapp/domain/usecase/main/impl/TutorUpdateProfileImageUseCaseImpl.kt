package com.akmaldev.tutorapp.domain.usecase.main.impl

import com.akmaldev.tutorapp.data.model.response.main.tutorprofile.TutorUpdateProfileImageResponseData
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.domain.usecase.main.TutorUpdateProfileImageUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import okhttp3.MultipartBody
import javax.inject.Inject

class TutorUpdateProfileImageUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    TutorUpdateProfileImageUseCase {
    override suspend fun invoke(image: MultipartBody.Part): WrappedResponse<TutorUpdateProfileImageResponseData> =
        mainRepository.tutorUpdateProfileImage(image)
}