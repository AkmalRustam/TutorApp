package com.akmaldev.tutorapp.presentation.ui.tutor.viewmodel

import com.akmaldev.tutorapp.data.model.response.main.tutor.TutorStatisticsResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import kotlinx.coroutines.flow.SharedFlow

interface TutorViewModel {
    val statisticsFlow: SharedFlow<WrappedResponse<TutorStatisticsResponseData>>
    fun getTutorStatistics()
}