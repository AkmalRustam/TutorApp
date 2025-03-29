package com.akmaldev.tutorapp.data.model.response.auth._auth

data class Tutor(
    val _id: String,
    val login: String,
    val name: String,
    val role: String,
    val createdAt: String,
    val phone: String,
    val image: String,
    val updatedAt: String,
    val group: List<TutorGroup>
)
