package com.akmaldev.tutorapp.presentation.ui.auth.viewmodel

import com.akmaldev.tutorapp.data.model.request.auth._auth.StudentLoginRequestData
import com.akmaldev.tutorapp.data.model.request.auth._auth.TutorLoginRequestData
import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentLoginResponseData
import com.akmaldev.tutorapp.data.model.response.auth._auth.TutorLoginResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import kotlinx.coroutines.flow.SharedFlow

interface AuthViewModel {
    val studentFlow: SharedFlow<WrappedResponse<StudentLoginResponseData>>
    val tutorFlow: SharedFlow<WrappedResponse<TutorLoginResponseData>>

    fun studentLogin(studentLoginRequestData: StudentLoginRequestData)
    fun tutorLogin(tutorLoginRequestData: TutorLoginRequestData)
}