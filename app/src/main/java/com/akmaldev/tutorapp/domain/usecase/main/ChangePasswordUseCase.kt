package com.akmaldev.tutorapp.domain.usecase.main

import com.akmaldev.tutorapp.data.model.request.main.tutorchangepassword.TutorChangePasswordRequestData
import com.akmaldev.tutorapp.data.model.response.main.tutorchangepassword.TutorChangePasswordResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse

interface ChangePasswordUseCase {
    suspend operator fun invoke(requestData: TutorChangePasswordRequestData): WrappedResponse<TutorChangePasswordResponseData>
}