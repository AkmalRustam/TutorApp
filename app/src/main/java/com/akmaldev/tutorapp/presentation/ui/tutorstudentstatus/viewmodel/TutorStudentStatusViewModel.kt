package com.akmaldev.tutorapp.presentation.ui.tutorstudentstatus.viewmodel

import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.CheckRequestData
import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.PushNotificationRequestData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.CheckResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.PushNotificationResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.StudentByStudentIdResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import kotlinx.coroutines.flow.SharedFlow

interface TutorStudentStatusViewModel {
    val studentFlow: SharedFlow<WrappedResponse<StudentByStudentIdResponseData>>
    val checkFlow: SharedFlow<WrappedResponse<CheckResponseData>>
    val buttonNextVisibilityFlow: SharedFlow<Unit>
    val pushNotificationFlow: SharedFlow<WrappedResponse<PushNotificationResponseData>>
    val reportFlow: SharedFlow<WrappedResponse<PushNotificationResponseData>>

    fun getStudentByStudentId(studentId: String)
    fun check(checkRequestData: CheckRequestData)
    fun refreshButtonNextVisibility()
    fun pushNotification(requestData: PushNotificationRequestData)
    fun report(requestData: PushNotificationRequestData)
}