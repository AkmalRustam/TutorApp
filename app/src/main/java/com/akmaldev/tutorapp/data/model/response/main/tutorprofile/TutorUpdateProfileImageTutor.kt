package com.akmaldev.tutorapp.data.model.response.main.tutorprofile

data class TutorUpdateProfileImageTutor(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val group: List<TutorUpdateProfileImageGroup>,
    val image: String,
    val login: String,
    val name: String,
    val password: String,
    val phone: String,
    val role: String,
    val updatedAt: String
)