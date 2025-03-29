package com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus

data class PushNotificationRequestData(
    val appartmentId: String,
    val message: String,
    val status: String,
    val userId: String
)