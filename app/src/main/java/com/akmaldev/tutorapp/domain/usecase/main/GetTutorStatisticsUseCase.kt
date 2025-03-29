package com.akmaldev.tutorapp.domain.usecase.main

import com.akmaldev.tutorapp.data.model.response.main.tutor.TutorStatisticsResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse

interface GetTutorStatisticsUseCase {
    suspend operator fun invoke(): WrappedResponse<TutorStatisticsResponseData>
}