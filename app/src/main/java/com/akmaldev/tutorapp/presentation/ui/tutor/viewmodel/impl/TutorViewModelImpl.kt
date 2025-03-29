package com.akmaldev.tutorapp.presentation.ui.tutor.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmaldev.tutorapp.data.model.response.main.tutor.TutorStatisticsResponseData
import com.akmaldev.tutorapp.domain.usecase.main.GetTutorStatisticsUseCase
import com.akmaldev.tutorapp.presentation.ui.tutor.viewmodel.TutorViewModel
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorViewModelImpl @Inject constructor(
    private val getTutorStatisticsUseCase: GetTutorStatisticsUseCase
) : ViewModel(), TutorViewModel {

    private val _statisticsFlow: MutableSharedFlow<WrappedResponse<TutorStatisticsResponseData>> =
        MutableSharedFlow()
    override val statisticsFlow: SharedFlow<WrappedResponse<TutorStatisticsResponseData>>
        get() = _statisticsFlow

    override fun getTutorStatistics() {
        viewModelScope.launch {
            val response = getTutorStatisticsUseCase()
            _statisticsFlow.emit(WrappedResponse.Loading)
            _statisticsFlow.emit(response)
        }
    }
}