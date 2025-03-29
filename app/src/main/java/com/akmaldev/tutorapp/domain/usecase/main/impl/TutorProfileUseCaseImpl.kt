package com.akmaldev.tutorapp.domain.usecase.main.impl

import com.akmaldev.tutorapp.data.model.response.main.tutorprofile.TutorProfileResponseData
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.domain.usecase.main.TutorProfileUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import javax.inject.Inject

class TutorProfileUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    TutorProfileUseCase {
    override suspend fun invoke(): WrappedResponse<TutorProfileResponseData> =
        mainRepository.tutorProfile()
}