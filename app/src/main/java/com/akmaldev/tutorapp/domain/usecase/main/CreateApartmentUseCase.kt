package com.akmaldev.tutorapp.domain.usecase.main

import com.akmaldev.tutorapp.data.model.response.main.question.ApartmentResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface CreateApartmentUseCase {
    suspend operator fun invoke(
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
}