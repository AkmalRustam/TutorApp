package com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus

data class StudentByStudentIdResponseData(
    val `data`: List<StudentByStudentIdData>,
    val status: String
)