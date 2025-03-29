package com.akmaldev.tutorapp.data.model.response.main.studentstatus

data class Faculty(
    val active: Boolean,
    val code: String,
    val id: Int,
    val localityType: LocalityType,
    val name: String,
    val parent: Any,
    val structureType: StructureType
)