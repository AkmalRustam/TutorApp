package com.akmaldev.tutorapp.data.model.response.main.studentstatus

import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentGender
import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentProvince

data class Student(
    val faculty: Faculty,
    val full_name: String,
    val group: Group,
    val image: String,
    val province: StudentProvince,
    val gender: StudentGender
)