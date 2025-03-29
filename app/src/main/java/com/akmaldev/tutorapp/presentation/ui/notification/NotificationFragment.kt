package com.akmaldev.tutorapp.presentation.ui.notification

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.akmaldev.tutorapp.databinding.FragmentNotificationBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.notification.adapter.NotificationAdapter
import com.akmaldev.tutorapp.presentation.ui.notification.viewmodel.impl.NotificationsViewModelImpl
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.extension.toastMessage
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class NotificationFragment :
    BaseFragment<FragmentNotificationBinding>(FragmentNotificationBinding::inflate) {

    private val notificationAdapter: NotificationAdapter = NotificationAdapter()
    private val viewModel: NotificationsViewModelImpl by viewModels()

    override fun FragmentNotificationBinding.loadDatasFromNetwork() {
        viewModel.getNotifications()
    }

    override fun FragmentNotificationBinding.setViews() {
        recyclerViewNotification.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = notificationAdapter
        }
    }

    override fun FragmentNotificationBinding.setObservables() {
        viewModel.notificationFlow.onEach {
            when (it) {
                WrappedResponse.Loading -> {}
                is WrappedResponse.Error -> {
                    Log.d(TAG, "Response error: ${it.message}")
                    toastMessage(it.message)
                }

                is WrappedResponse.Success -> {
                    notificationAdapter.submitList(it.data.data)
                }
            }
        }.launchIn(lifecycleScope)
    }

    override fun FragmentNotificationBinding.setListeners() {
        notificationAdapter.setOnItemClickListener {
            when (it.status) {
                RED -> {}
                GREEN -> {}
                YELLOW -> {}
            }
        }
        ivBack.setOnClickListener {
            popBackStack()
        }
    }

    companion object {
        private const val RED = "red"
        private const val GREEN = "green"
        private const val YELLOW = "yellow"
        private const val TAG = "NotificationFragmentTag"
    }
}