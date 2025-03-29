package com.akmaldev.tutorapp.presentation.ui.tutorprofile.viewmodel

import com.akmaldev.tutorapp.data.model.response.main.tutorprofile.TutorProfileResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorprofile.TutorUpdateProfileImageResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import kotlinx.coroutines.flow.SharedFlow
import okhttp3.MultipartBody

interface TutorProfileViewModel {
    val profile: SharedFlow<WrappedResponse<TutorProfileResponseData>>
    val updateProfileImageFlow: SharedFlow<WrappedResponse<TutorUpdateProfileImageResponseData>>
    fun tutorProfile()
    fun updateProfileImage(image: MultipartBody.Part)
}