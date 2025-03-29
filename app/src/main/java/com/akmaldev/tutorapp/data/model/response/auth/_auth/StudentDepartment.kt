package com.akmaldev.tutorapp.data.model.response.auth._auth

data class StudentDepartment(
    val id: Int,
    val name: String,
    val code: String,
    val structureType: StudentDepartmentStructureType,
    val localityType: StudentDepartmentLocalityType,
    val parent: String?,
    val active: Boolean
)
