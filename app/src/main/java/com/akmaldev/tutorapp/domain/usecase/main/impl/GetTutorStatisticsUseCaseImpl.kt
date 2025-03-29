package com.akmaldev.tutorapp.domain.usecase.main.impl

import com.akmaldev.tutorapp.data.model.response.main.tutor.TutorStatisticsResponseData
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.domain.usecase.main.GetTutorStatisticsUseCase
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import javax.inject.Inject

class GetTutorStatisticsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository): GetTutorStatisticsUseCase {
    override suspend fun invoke(): WrappedResponse<TutorStatisticsResponseData> = mainRepository.getTutorStatistics()
}