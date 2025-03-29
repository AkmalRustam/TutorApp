package com.akmaldev.tutorapp.data.model.response.main.studentstatus

data class Pagination(
    val limit: Int,
    val nextPage: Int,
    val page: Int,
    val prevPage: Int,
    val total: Int,
    val totalPages: Int
)