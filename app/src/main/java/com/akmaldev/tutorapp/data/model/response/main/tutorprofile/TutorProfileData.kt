package com.akmaldev.tutorapp.data.model.response.main.tutorprofile

data class TutorProfileData(
    val _id: String,
    val createdAt: String,
    val group: List<TutorProfileDataGroup>,
    val login: String,
    val name: String,
    val password: String,
    val role: String,
    val updatedAt: String,
    val image: String?,
    val phone: String
)