package com.akmaldev.tutorapp.presentation.ui.profile.viewmodel

import com.akmaldev.tutorapp.data.model.response.main.profile.StudentProfileResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import kotlinx.coroutines.flow.SharedFlow

interface ProfileViewModel {
    val studentProfileFlow: SharedFlow<WrappedResponse<StudentProfileResponseData>>
    fun getStudentProfile()
}