package com.akmaldev.tutorapp.domain.usecase.main

import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.StudentByStudentIdResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse

interface StudentByStudentIdUseCase {
    suspend operator fun invoke(studentId: String): WrappedResponse<StudentByStudentIdResponseData>
}