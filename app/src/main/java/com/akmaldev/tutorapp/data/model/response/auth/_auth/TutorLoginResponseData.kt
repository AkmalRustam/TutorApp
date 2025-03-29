package com.akmaldev.tutorapp.data.model.response.auth._auth

data class TutorLoginResponseData(
    val status: String,
    val data: Tutor,
    val token: String
)