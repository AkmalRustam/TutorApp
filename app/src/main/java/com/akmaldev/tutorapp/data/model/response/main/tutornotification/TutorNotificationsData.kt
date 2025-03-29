package com.akmaldev.tutorapp.data.model.response.main.tutornotification

data class TutorNotificationsData(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val isRead: Boolean,
    val message: String,
    val updatedAt: String,
    val userId: String
)