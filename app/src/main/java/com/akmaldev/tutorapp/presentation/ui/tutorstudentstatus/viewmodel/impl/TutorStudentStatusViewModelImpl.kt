package com.akmaldev.tutorapp.presentation.ui.tutorstudentstatus.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.CheckRequestData
import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.PushNotificationRequestData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.CheckResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.PushNotificationResponseData
import com.akmaldev.tutorapp.data.model.response.main.tutorstudentstatus.StudentByStudentIdResponseData
import com.akmaldev.tutorapp.domain.usecase.main.CheckUseCase
import com.akmaldev.tutorapp.domain.usecase.main.PushNotificationUseCase
import com.akmaldev.tutorapp.domain.usecase.main.ReportUseCase
import com.akmaldev.tutorapp.domain.usecase.main.StudentByStudentIdUseCase
import com.akmaldev.tutorapp.presentation.ui.tutorstudentstatus.viewmodel.TutorStudentStatusViewModel
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorStudentStatusViewModelImpl @Inject constructor(
    private val studentByStudentIdUseCase: StudentByStudentIdUseCase,
    private val checkUseCase: CheckUseCase,
    private val pushNotificationUseCase: PushNotificationUseCase,
    private val reportUseCase: ReportUseCase
) :
    ViewModel(),
    TutorStudentStatusViewModel {

    private val _studentFlow: MutableSharedFlow<WrappedResponse<StudentByStudentIdResponseData>> =
        MutableSharedFlow()
    override val studentFlow: SharedFlow<WrappedResponse<StudentByStudentIdResponseData>>
        get() = _studentFlow

    private val _checkFlow: MutableSharedFlow<WrappedResponse<CheckResponseData>> =
        MutableSharedFlow()
    override val checkFlow: SharedFlow<WrappedResponse<CheckResponseData>>
        get() = _checkFlow
    private val _buttonNextVisibilityFlow: MutableSharedFlow<Unit> = MutableSharedFlow()
    override val buttonNextVisibilityFlow: SharedFlow<Unit>
        get() = _buttonNextVisibilityFlow

    private val _pushNotificationFlow: MutableSharedFlow<WrappedResponse<PushNotificationResponseData>> =
        MutableSharedFlow()
    override val pushNotificationFlow: SharedFlow<WrappedResponse<PushNotificationResponseData>>
        get() = _pushNotificationFlow

    private val _reportFlow: MutableSharedFlow<WrappedResponse<PushNotificationResponseData>> =
        MutableSharedFlow()
    override val reportFlow: SharedFlow<WrappedResponse<PushNotificationResponseData>>
        get() = _reportFlow

    override fun getStudentByStudentId(studentId: String) {
        viewModelScope.launch {
            val response = studentByStudentIdUseCase(studentId)
            _studentFlow.emit(WrappedResponse.Loading)
            _studentFlow.emit(response)
        }
    }

    override fun check(checkRequestData: CheckRequestData) {
        viewModelScope.launch {
            val response = checkUseCase(checkRequestData)
            _checkFlow.emit(WrappedResponse.Loading)
            _checkFlow.emit(response)
        }
    }

    override fun refreshButtonNextVisibility() {
        viewModelScope.launch {
            _buttonNextVisibilityFlow.emit(Unit)
        }
    }

    override fun pushNotification(requestData: PushNotificationRequestData) {
        viewModelScope.launch {
            val response = pushNotificationUseCase(requestData)
            _pushNotificationFlow.emit(WrappedResponse.Loading)
            _pushNotificationFlow.emit(response)
        }
    }

    override fun report(requestData: PushNotificationRequestData) {
        viewModelScope.launch {
            val response = reportUseCase(requestData)
            _reportFlow.emit(WrappedResponse.Loading)
            _reportFlow.emit(response)
        }
    }
}