package com.akmaldev.tutorapp.presentation.ui.tutorchangepassword.viewmodel

import com.akmaldev.tutorapp.data.model.request.main.tutorchangepassword.TutorChangePasswordRequestData
import com.akmaldev.tutorapp.data.model.response.main.tutorchangepassword.TutorChangePasswordResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import kotlinx.coroutines.flow.SharedFlow

interface TutorChangePasswordViewModel {
    val changePasswordFlow: SharedFlow<WrappedResponse<TutorChangePasswordResponseData>>
    fun changePassword(requestData: TutorChangePasswordRequestData)
}