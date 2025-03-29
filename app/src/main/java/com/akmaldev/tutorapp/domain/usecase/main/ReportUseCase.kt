package com.akmaldev.tutorapp.domain.usecase.main

import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.PushNotificationRequestData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.PushNotificationResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse

interface ReportUseCase {
    suspend operator fun invoke(requestData: PushNotificationRequestData): WrappedResponse<PushNotificationResponseData>
}