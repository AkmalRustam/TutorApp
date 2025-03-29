package com.akmaldev.tutorapp.presentation.ui.question.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmaldev.tutorapp.data.model.response.main.question.ApartmentResponseData
import com.akmaldev.tutorapp.domain.usecase.main.CreateApartmentUseCase
import com.akmaldev.tutorapp.presentation.ui.question.viewmodel.QuestionViewModel
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class QuestionViewModelImpl @Inject constructor(
    private val createApartmentUseCase: CreateApartmentUseCase
) : ViewModel(), QuestionViewModel {

    private val _createApartmentFlow: MutableSharedFlow<WrappedResponse<ApartmentResponseData>> =
        MutableSharedFlow()
    override val createApartmentFlow: SharedFlow<WrappedResponse<ApartmentResponseData>> get() = _createApartmentFlow

    override fun createApartment(
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
    ) {
        viewModelScope.launch {
            val response = createApartmentUseCase(
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
            _createApartmentFlow.emit(WrappedResponse.Loading)
            _createApartmentFlow.emit(response)
        }
    }
}