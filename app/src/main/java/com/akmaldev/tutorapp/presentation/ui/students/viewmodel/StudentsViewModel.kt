package com.akmaldev.tutorapp.presentation.ui.students.viewmodel

import androidx.paging.PagingData
import com.akmaldev.tutorapp.data.model.response.main.students.StudentsData
import kotlinx.coroutines.flow.Flow

interface StudentsViewModel {
    fun getStudents(groupName: String): Flow<PagingData<StudentsData>>
}