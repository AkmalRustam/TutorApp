package com.akmaldev.tutorapp.data.model.response.main.studentstatus

data class StudentByStatusResponseData(
    val `data`: List<Data>,
    val pagination: Pagination,
    val status: String
)