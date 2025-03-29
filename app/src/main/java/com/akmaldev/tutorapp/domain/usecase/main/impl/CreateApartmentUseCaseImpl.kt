package com.akmaldev.tutorapp.domain.usecase.main.impl

import com.akmaldev.tutorapp.data.model.response.main.question.ApartmentResponseData
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.domain.usecase.main.CreateApartmentUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class CreateApartmentUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
) : CreateApartmentUseCase {
    override suspend fun invoke(
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
        addition: RequestBody,
    ): WrappedResponse<ApartmentResponseData> = mainRepository.createApartment(
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
    )
}