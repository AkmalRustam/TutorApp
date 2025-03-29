package com.akmaldev.tutorapp.presentation.ui.tutornotification

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.akmaldev.tutorapp.databinding.FragmentTutorNotificationBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.tutornotification.adapter.TutorNotificationAdapter
import com.akmaldev.tutorapp.presentation.ui.tutornotification.viewmodel.impl.TutorNotificationViewModelImpl
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.extension.toastMessage
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class TutorNotificationFragment :
    BaseFragment<FragmentTutorNotificationBinding>(FragmentTutorNotificationBinding::inflate) {

    @Inject lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private var tutorNotificationAdapter = TutorNotificationAdapter()

    private val viewModel: TutorNotificationViewModelImpl by viewModels()

    override fun FragmentTutorNotificationBinding.setObservables() {
        viewModel.notificationsFlow.onEach {
            when(it) {
                WrappedResponse.Loading -> {}
                is WrappedResponse.Error -> toastMessage(it.message)
                is WrappedResponse.Success -> {
                    tutorNotificationAdapter.submitList(it.data.data)
                }
            }
        }.launchIn(lifecycleScope)
    }

    override fun FragmentTutorNotificationBinding.setListeners() {
        ivBack.setOnClickListener {
            popBackStack()
        }
    }

    override fun FragmentTutorNotificationBinding.loadDatasFromNetwork() {
        viewModel.getTutorNotifications(sharedPreferencesHelper.tutorId)
    }

    override fun FragmentTutorNotificationBinding.setViews() {
        recyclerViewNotification.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = tutorNotificationAdapter
        }
    }
}