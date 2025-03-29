package com.akmaldev.tutorapp.domain.usecase.main.impl

import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.CheckRequestData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.CheckResponseData
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.domain.usecase.main.CheckUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import javax.inject.Inject

class CheckUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    CheckUseCase {
    override suspend fun invoke(requestData: CheckRequestData): WrappedResponse<CheckResponseData> = mainRepository.check(requestData)}