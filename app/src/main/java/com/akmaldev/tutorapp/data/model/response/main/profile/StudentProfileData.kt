package com.akmaldev.tutorapp.data.model.response.main.profile

import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentGender

data class StudentProfileData(
    val gender: StudentGender,
    val image: String?,
    val province: String
)