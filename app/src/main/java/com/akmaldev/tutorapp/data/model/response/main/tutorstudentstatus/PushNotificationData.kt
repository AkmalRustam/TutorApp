package com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus

data class PushNotificationData(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val isRead: Boolean,
    val message: String,
    val status: String,
    val updatedAt: String,
    val userId: String
)