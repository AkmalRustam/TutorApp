package com.akmaldev.tutorapp.presentation.ui.students.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.akmaldev.tutorapp.data.remote.pagination.StudentsPagingSource
import com.akmaldev.tutorapp.data.remote.service.main.MainService
import com.akmaldev.tutorapp.presentation.ui.students.viewmodel.StudentsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StudentsViewModelImpl @Inject constructor(
    private val mainService: MainService
) : ViewModel(), StudentsViewModel {

    override fun getStudents(groupName: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            initialLoadSize = 40,
            prefetchDistance = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { StudentsPagingSource(mainService, groupName) }
    ).flow.cachedIn(viewModelScope)
}