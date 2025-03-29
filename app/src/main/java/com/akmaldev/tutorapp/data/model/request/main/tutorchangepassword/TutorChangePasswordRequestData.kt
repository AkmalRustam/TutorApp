package com.akmaldev.tutorapp.data.model.request.main.tutorchangepassword

data class TutorChangePasswordRequestData(
    val confirmPassword: String,
    val newPassword: String,
)