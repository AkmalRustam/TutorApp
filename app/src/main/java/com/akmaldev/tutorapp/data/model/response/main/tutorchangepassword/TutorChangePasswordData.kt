package com.akmaldev.tutorapp.data.model.response.main.tutorchangepassword

data class TutorChangePasswordData(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val group: List<TutorChangePasswordDataGroup>,
    val image: String,
    val login: String,
    val name: String,
    val password: String,
    val role: String,
    val updatedAt: String
)