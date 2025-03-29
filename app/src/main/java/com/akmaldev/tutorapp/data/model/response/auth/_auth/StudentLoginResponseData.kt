package com.akmaldev.tutorapp.data.model.response.auth._auth

data class StudentLoginResponseData(
    val status: String,
    val student: Student,
    val token: String
)
