package com.akmaldev.tutorapp.presentation.ui.question.viewmodel

import com.akmaldev.tutorapp.data.model.response.main.question.ApartmentResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import kotlinx.coroutines.flow.SharedFlow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface QuestionViewModel {
    val createApartmentFlow: SharedFlow<WrappedResponse<ApartmentResponseData>>
    fun createApartment(
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
    )
}