package com.akmaldev.tutorapp.data.repository.main.impl

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
import com.akmaldev.tutorapp.data.remote.service.main.MainService
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.util.extension.toWrappedResponse
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val mainService: MainService) :
    MainRepository {
    override suspend fun getNotifications(): WrappedResponse<NotificationResponseData> {
        return mainService.getNotifications().toWrappedResponse()
    }

    override suspend fun createApartment(
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
    ): WrappedResponse<ApartmentResponseData> {
        return mainService.createApartment(
            studentId,
            studentPhoneNumber,
            district,
            fullAddress,
            smallDistrict,
            typeOfAppartment,
            contract,
            typeOfBoiler,
            priceAppartment,
            numberOfStudents,
            appartmentOwnerName,
            appartmentOwnerPhone,
            appartmentNumber,
            boilerImage,
            gazStove,
            chimney,
            additionImage,
            lat,
            lon,
            addition
        ).toWrappedResponse()
    }

    override suspend fun getStudentProfile(): WrappedResponse<StudentProfileResponseData> {
        return mainService.getStudentProfile().toWrappedResponse()
    }

    override suspend fun getTutorStatistics(): WrappedResponse<TutorStatisticsResponseData> {
        return mainService.getTutorStatistics().toWrappedResponse()
    }

    override suspend fun changePassword(requestData: TutorChangePasswordRequestData): WrappedResponse<TutorChangePasswordResponseData> {
        return mainService.changePassword(requestData).toWrappedResponse()
    }

    override suspend fun tutorProfile(): WrappedResponse<TutorProfileResponseData> {
        return mainService.tutorProfile().toWrappedResponse()
    }

    override suspend fun getTutorNotifications(tutorId: String): WrappedResponse<TutorNotificationsResponseData> {
        return mainService.getTutorNotifications(tutorId).toWrappedResponse()
    }

    override suspend fun getStudentByStudentId(studentId: String): WrappedResponse<StudentByStudentIdResponseData> {
        return mainService.getStudentByStudentId(studentId).toWrappedResponse()
    }

    override suspend fun check(requestData: CheckRequestData): WrappedResponse<CheckResponseData> {
        return mainService.check(requestData).toWrappedResponse()
    }

    override suspend fun tutorUpdateProfileImage(image: MultipartBody.Part): WrappedResponse<TutorUpdateProfileImageResponseData> {
        return mainService.tutorUpdateProfileImage(image).toWrappedResponse()
    }

    override suspend fun pushNotification(requestData: PushNotificationRequestData): WrappedResponse<PushNotificationResponseData> {
        return mainService.pushNotification(requestData).toWrappedResponse()
    }

    override suspend fun report(requestData: PushNotificationRequestData): WrappedResponse<PushNotificationResponseData> {
        return mainService.report(requestData).toWrappedResponse()
    }
}