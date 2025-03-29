package com.akmaldev.tutorapp.data.model.response.main.question

data class ApartmentData(
    val __v: Int,
    val _id: String,
    val appartmentNumber: String,
    val appartmentOwnerName: String,
    val appartmentOwnerPhone: String,
    val boilerImage: ApartmentDataBoilerImage,
    val chimney: ApartmentDataChimney,
    val contract: Boolean,
    val createdAt: String,
    val current: Boolean,
    val district: String,
    val fullAddress: String,
    val gazStove: ApartmentDataGazStove,
    val location: ApartmentDataLocation,
    val needNew: Boolean,
    val numberOfStudents: Int,
    val priceAppartment: Int,
    val smallDistrict: String,
    val status: String,
    val studentId: String,
    val studentPhoneNumber: String,
    val typeOfAppartment: String,
    val typeOfBoiler: String,
    val updatedAt: String,
    val view: Boolean
)