package com.akmaldev.tutorapp.domain.usecase.main.impl

import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.StudentByStudentIdResponseData
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.domain.usecase.main.StudentByStudentIdUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import javax.inject.Inject

class StudentByStudentIdUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    StudentByStudentIdUseCase {
    override suspend fun invoke(studentId: String): WrappedResponse<StudentByStudentIdResponseData> =
        mainRepository.getStudentByStudentId(studentId)
}