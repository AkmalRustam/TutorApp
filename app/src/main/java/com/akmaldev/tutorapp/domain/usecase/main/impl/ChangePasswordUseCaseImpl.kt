package com.akmaldev.tutorapp.domain.usecase.main.impl

import com.akmaldev.tutorapp.data.model.request.main.tutorchangepassword.TutorChangePasswordRequestData
import com.akmaldev.tutorapp.data.model.response.main.tutorchangepassword.TutorChangePasswordResponseData
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.domain.usecase.main.ChangePasswordUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import javax.inject.Inject

class ChangePasswordUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) : ChangePasswordUseCase {
    override suspend fun invoke(requestData: TutorChangePasswordRequestData): WrappedResponse<TutorChangePasswordResponseData> = mainRepository.changePassword(requestData)
}