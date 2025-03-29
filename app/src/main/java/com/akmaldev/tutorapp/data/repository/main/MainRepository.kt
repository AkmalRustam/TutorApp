package com.akmaldev.tutorapp.data.repository.main

import com.akmaldev.tutorapp.data.model.request.main.tutorchangepassword.TutorChangePasswordRequestData
import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.CheckRequestData
import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.PushNotificationRequestData
import com.akmaldev.tutorapp.data.model.response.main.notification.NotificationResponseData
import com.akmaldev.tutorapp.data.model.response.main.profile.StudentProfileResponseData
import com.akmaldev.tutorapp.data.model.response.main.question.ApartmentResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutor.TutorStatisticsResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorchangepassword.TutorChangePasswordResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutornotification.TutorNotificationsResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorprofile.TutorProfileResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorprofile.TutorUpdateProfileImageResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.CheckResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.PushNotificationResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.StudentByStudentIdResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body

interface MainRepository {
    suspend fun getNotifications(): WrappedResponse<NotificationResponseData>
    suspend fun createApartment(
        studentId: RequestBody,
        studentPhoneNumber: RequestBody,
        district: RequestBody,
        fullAddress: RequestBody,
        smallDistrict: RequestBody,
        typeOfAppartment: RequestBody,
        contract: RequestBody,
        typeOfBoiler: RequestBody,
        priceAppartment: RequestBody,
        numberOfStudents: RequestBody,
        appartmentOwnerName: RequestBody,
        appartmentOwnerPhone: RequestBody,
        appartmentNumber: RequestBody,
        boilerImage: MultipartBody.Part?,
        gazStove: MultipartBody.Part?,
        chimney: MultipartBody.Part?,
        additionImage: MultipartBody.Part?,
        lat: RequestBody,
        lon: RequestBody,
        addition: RequestBody
    ): WrappedResponse<ApartmentResponseData>

    suspend fun getStudentProfile(): WrappedResponse<StudentProfileResponseData>
    suspend fun getTutorStatistics(): WrappedResponse<TutorStatisticsResponseData>
    suspend fun changePassword(requestData: TutorChangePasswordRequestData): WrappedResponse<TutorChangePasswordResponseData>
    suspend fun tutorProfile(): WrappedResponse<TutorProfileResponseData>
    suspend fun getTutorNotifications(tutorId: String): WrappedResponse<TutorNotificationsResponseData>
    suspend fun getStudentByStudentId(
        studentId: String
    ): WrappedResponse<StudentByStudentIdResponseData>

    suspend fun check(requestData: CheckRequestData): WrappedResponse<CheckResponseData>
    suspend fun tutorUpdateProfileImage(
        image: MultipartBody.Part
    ): WrappedResponse<TutorUpdateProfileImageResponseData>
    suspend fun pushNotification(requestData: PushNotificationRequestData): WrappedResponse<PushNotificationResponseData>
    suspend fun report(requestData: PushNotificationRequestData): WrappedResponse<PushNotificationResponseData>
}