package com.akmaldev.tutorapp.data.model.response.main.students

import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentGender
import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentProvince
import com.akmaldev.tutorapp.data.model.response.auth._auth.StudentUniversity

data class StudentsData(
    val _id: String,
    val first_name: String,
    val full_name: String,
    val group: StudentsDataGroup,
    val image: String,
    val second_name: String,
    val short_name: String,
    val third_name: String,
    val university: StudentUniversity,
    val gender: StudentGender,
    val status: String,
    val location: StudentsDataLocation?,
    val province: StudentProvince,
    val hasFormFilled: Boolean,
)