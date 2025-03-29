package com.akmaldev.tutorapp.presentation.ui.studentstatus.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.akmaldev.tutorapp.data.remote.pagination.StudentStatusPagingSource
import com.akmaldev.tutorapp.data.remote.service.main.MainService
import com.akmaldev.tutorapp.presentation.ui.studentstatus.viewmodel.StudentStatusViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StudentStatusViewModelImpl @Inject constructor(
    private val mainService: MainService
) : ViewModel(), StudentStatusViewModel {
    override fun getStudents(status: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            initialLoadSize = 40,
            prefetchDistance = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { StudentStatusPagingSource(mainService, status) }
    ).flow.cachedIn(viewModelScope)
}