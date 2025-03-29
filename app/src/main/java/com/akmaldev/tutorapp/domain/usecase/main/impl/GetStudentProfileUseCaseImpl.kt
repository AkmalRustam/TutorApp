package com.akmaldev.tutorapp.domain.usecase.main.impl

import com.akmaldev.tutorapp.data.model.response.main.profile.StudentProfileResponseData
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.domain.usecase.main.GetStudentProfileUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import javax.inject.Inject

class GetStudentProfileUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetStudentProfileUseCase {
    override suspend fun invoke(): WrappedResponse<StudentProfileResponseData> =
        mainRepository.getStudentProfile()
}