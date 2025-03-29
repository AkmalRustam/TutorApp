package com.akmaldev.tutorapp.domain.usecase.main

import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.CheckRequestData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.CheckResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse

interface CheckUseCase {
    suspend operator fun invoke(requestData: CheckRequestData): WrappedResponse<CheckResponseData>
}