package com.akmaldev.tutorapp.data.model.response.main.students

data class StudentsResponseData(
    val `data`: List<StudentsData>,
    val hasNextPage: Boolean,
    val hasPrevPage: Boolean,
    val limit: Int,
    val nextPage: Int?,
    val page: Int,
    val prevPage: Int?,
    val status: String,
    val totalPages: Int,
    val totalStudents: Int
)