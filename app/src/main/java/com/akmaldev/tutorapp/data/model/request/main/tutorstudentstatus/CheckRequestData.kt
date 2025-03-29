package com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus

data class CheckRequestData(
    val appartmentId: String,
    val boiler: String,
    val chimney: String,
    val gazStove: String,
    val status: String,
    val additionImage: String
)