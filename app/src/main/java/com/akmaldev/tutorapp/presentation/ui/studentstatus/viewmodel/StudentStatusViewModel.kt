package com.akmaldev.tutorapp.presentation.ui.studentstatus.viewmodel

import androidx.paging.PagingData
import com.akmaldev.tutorapp.data.model.response.main.studentstatus.Data
import kotlinx.coroutines.flow.Flow

interface StudentStatusViewModel {
    fun getStudents(status: String): Flow<PagingData<Data>>
}