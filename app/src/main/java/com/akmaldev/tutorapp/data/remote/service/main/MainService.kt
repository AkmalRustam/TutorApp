package com.akmaldev.tutorapp.data.remote.service.main

import com.akmaldev.tutorapp.data.model.request.main.tutorchangepassword.TutorChangePasswordRequestData
import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.CheckRequestData
import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.PushNotificationRequestData
import com.akmaldev.tutorapp.data.model.response.main.notification.NotificationResponseData
import com.akmaldev.tutorapp.data.model.response.main.profile.StudentProfileResponseData
import com.akmaldev.tutorapp.data.model.response.main.question.ApartmentResponseData
import com.akmaldev.tutorapp.data.model.response.main.students.StudentsResponseData
import com.akmaldev.tutorapp.data.model.response.main.studentstatus.StudentByStatusResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutor.TutorStatisticsResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorchangepassword.TutorChangePasswordResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutornotification.TutorNotificationsResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorprofile.TutorProfileResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorprofile.TutorUpdateProfileImageResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.CheckResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.PushNotificationResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.StudentByStudentIdResponseData
import com.akmaldev.tutorapp.util.constant.ServiceConstants.APARTMENT_CREATE
import com.akmaldev.tutorapp.util.constant.ServiceConstants.STUDENT_NOTIFICATION
import com.akmaldev.tutorapp.util.constant.ServiceConstants.STUDENT_PROFILE
import com.akmaldev.tutorapp.util.constant.ServiceConstants.TUTOR_CHANGE_PASSWORD
import com.akmaldev.tutorapp.util.constant.ServiceConstants.TUTOR_CHECK
import com.akmaldev.tutorapp.util.constant.ServiceConstants.TUTOR_EDIT_PROFILE
import com.akmaldev.tutorapp.util.constant.ServiceConstants.TUTOR_GROUP_STUDENTS
import com.akmaldev.tutorapp.util.constant.ServiceConstants.TUTOR_NOTIFICATIONS
import com.akmaldev.tutorapp.util.constant.ServiceConstants.TUTOR_PROFILE
import com.akmaldev.tutorapp.util.constant.ServiceConstants.TUTOR_PUSH_NOTIFICATION
import com.akmaldev.tutorapp.util.constant.ServiceConstants.TUTOR_REPORT
import com.akmaldev.tutorapp.util.constant.ServiceConstants.TUTOR_STATISTICS
import com.akmaldev.tutorapp.util.constant.ServiceConstants.TUTOR_STUDENTS_BY_STATUS
import com.akmaldev.tutorapp.util.constant.ServiceConstants.TUTOR_STUDENT_BY_STIDENT_ID
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface MainService {
    @GET(STUDENT_NOTIFICATION)
    suspend fun getNotifications(): Response<NotificationResponseData>

    @Multipart
    @POST(APARTMENT_CREATE)
    suspend fun createApartment(
        @Part("studentId") studentId: RequestBody,
        @Part("studentPhoneNumber") studentPhoneNumber: RequestBody,
        @Part("district") district: RequestBody,
        @Part("fullAddress") fullAddress: RequestBody,
        @Part("smallDistrict") smallDistrict: RequestBody,
        @Part("typeOfAppartment") typeOfAppartment: RequestBody,
        @Part("contract") contract: RequestBody,
        @Part("typeOfBoiler") typeOfBoiler: RequestBody,
        @Part("priceAppartment") priceAppartment: RequestBody,
        @Part("numberOfStudents") numberOfStudents: RequestBody,
        @Part("appartmentOwnerName") appartmentOwnerName: RequestBody,
        @Part("appartmentOwnerPhone") appartmentOwnerPhone: RequestBody,
        @Part("appartmentNumber") appartmentNumber: RequestBody,
        @Part boilerImage: MultipartBody.Part?,
        @Part gazStove: MultipartBody.Part?,
        @Part chimney: MultipartBody.Part?,
        @Part additionImage: MultipartBody.Part?,
        @Part("lat") lat: RequestBody,
        @Part("lon") lon: RequestBody,
        @Part("description") addition: RequestBody
    ): Response<ApartmentResponseData>

    @GET(STUDENT_PROFILE)
    suspend fun getStudentProfile(): Response<StudentProfileResponseData>

    @GET(TUTOR_STATISTICS)
    suspend fun getTutorStatistics(): Response<TutorStatisticsResponseData>

    @GET(TUTOR_GROUP_STUDENTS)
    suspend fun getStudentsByGroup(
        @Path("groupName") groupName: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = 20
    ): Response<StudentsResponseData>

    @GET(TUTOR_STUDENTS_BY_STATUS)
    suspend fun getStudentsByStatus(
        @Path("status") status: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = 20
    ): Response<StudentByStatusResponseData>

    @POST(TUTOR_CHANGE_PASSWORD)
    suspend fun changePassword(
        @Body requestData: TutorChangePasswordRequestData
    ): Response<TutorChangePasswordResponseData>

    @GET(TUTOR_PROFILE)
    suspend fun tutorProfile(): Response<TutorProfileResponseData>
    @GET(TUTOR_NOTIFICATIONS)
    suspend fun getTutorNotifications(
        @Path("tutorId") tutorId: String
    ): Response<TutorNotificationsResponseData>

    @GET(TUTOR_STUDENT_BY_STIDENT_ID)
    suspend fun getStudentByStudentId(
        @Path("studentId") studentId: String
    ): Response<StudentByStudentIdResponseData>

    @POST(TUTOR_CHECK)
    suspend fun check(@Body requestData: CheckRequestData): Response<CheckResponseData>

    @Multipart
    @PUT(TUTOR_EDIT_PROFILE)
    suspend fun tutorUpdateProfileImage(
        @Part image: MultipartBody.Part
    ): Response<TutorUpdateProfileImageResponseData>

    @POST(TUTOR_PUSH_NOTIFICATION)
    suspend fun pushNotification(@Body requestData: PushNotificationRequestData): Response<PushNotificationResponseData>

    @POST(TUTOR_REPORT)
    suspend fun report(@Body requestData: PushNotificationRequestData): Response<PushNotificationResponseData>
}