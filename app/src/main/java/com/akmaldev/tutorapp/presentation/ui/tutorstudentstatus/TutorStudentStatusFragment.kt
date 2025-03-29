package com.akmaldev.tutorapp.presentation.ui.tutorstudentstatus

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.CheckRequestData
import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.PushNotificationRequestData
import com.akmaldev.tutorapp.databinding.TutorStudentStatusBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.tutorstudentstatus.viewmodel.impl.TutorStudentStatusViewModelImpl
import com.akmaldev.tutorapp.util.constant.AppConstants.BASE_URL
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.extension.toastMessage
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@AndroidEntryPoint
class TutorStudentStatusFragment :
    BaseFragment<TutorStudentStatusBinding>(TutorStudentStatusBinding::inflate) {

    private var boilerStatus: String = EMPTY_STRING
    private var gasStatus: String = EMPTY_STRING
    private var chimneyStatus: String = EMPTY_STRING
    private var additonStatus: String = EMPTY_STRING
    private var status: String = EMPTY_STRING
    private var studentId: String = EMPTY_STRING
    private var apartmentId: String? = null

    private val viewModel: TutorStudentStatusViewModelImpl by viewModels()
    private val args: TutorStudentStatusFragmentArgs by navArgs()

    @SuppressLint("ClickableViewAccessibility")
    override fun TutorStudentStatusBinding.setViews() {
        disactiveNextButton()
        when (args.studentStatus) {
            GREEN, YELLOW, RED -> {
                btnNext.isVisible = false
                greenBoilerContainer.setOnTouchListener { _, _ -> true }
                yellowBoilerContainer.setOnTouchListener { _, _ -> true }
                redBoilerContainer.setOnTouchListener { _, _ -> true }
                greenGasContainer.setOnTouchListener { _, _ -> true }
                yellowGasContainer.setOnTouchListener { _, _ -> true }
                redGasContainer.setOnTouchListener { _, _ -> true }
                greenChimneyContainer.setOnTouchListener { _, _ -> true }
                yellowChimneyContainer.setOnTouchListener { _, _ -> true }
                redChimneyContainer.setOnTouchListener { _, _ -> true }
                greenAdditionContainer.setOnTouchListener { _, _ -> true }
                yellowAdditionContainer.setOnTouchListener { _, _ -> true }
                redAdditionContainer.setOnTouchListener { _, _ -> true }
            }

            else -> {
                btnNext.isVisible = true
            }
        }
        tvTutorName.text = args.studentFullName
    }

    override fun TutorStudentStatusBinding.loadDatasFromNetwork() {
        viewModel.getStudentByStudentId(args.studentId)
    }

    override fun TutorStudentStatusBinding.setListeners() {
        ivBack.setOnClickListener {
            popBackStack()
        }
        ivReport.setOnClickListener {
            apartmentId?.let { _apartmentId ->
                val direction =
                    TutorStudentStatusFragmentDirections.actionTutorStudentStatusFragmentToReportDialogFragment(
                        _apartmentId,
                        studentId
                    )
                navigateTo(direction)
            }
        }
        greenBoilerContainer.setOnClickListener {
            boilerStatus = GREEN
            updateBoilerStatusBackgroundByBoilerStatus(boilerStatus)
            viewModel.refreshButtonNextVisibility()
        }
        yellowBoilerContainer.setOnClickListener {
            boilerStatus = YELLOW
            updateBoilerStatusBackgroundByBoilerStatus(boilerStatus)
            viewModel.refreshButtonNextVisibility()
        }
        redBoilerContainer.setOnClickListener {
            boilerStatus = RED
            updateBoilerStatusBackgroundByBoilerStatus(boilerStatus)
            viewModel.refreshButtonNextVisibility()
        }
        greenGasContainer.setOnClickListener {
            gasStatus = GREEN
            updateGasStatusBackgroundByGasStatus(gasStatus)
            viewModel.refreshButtonNextVisibility()
        }
        yellowGasContainer.setOnClickListener {
            gasStatus = YELLOW
            updateGasStatusBackgroundByGasStatus(gasStatus)
            viewModel.refreshButtonNextVisibility()
        }
        redGasContainer.setOnClickListener {
            gasStatus = RED
            updateGasStatusBackgroundByGasStatus(gasStatus)
            viewModel.refreshButtonNextVisibility()
        }
        greenChimneyContainer.setOnClickListener {
            chimneyStatus = GREEN
            updateChimneyStatusBackgroundByChimneyStatus(chimneyStatus)
            viewModel.refreshButtonNextVisibility()
        }
        yellowChimneyContainer.setOnClickListener {
            chimneyStatus = YELLOW
            updateChimneyStatusBackgroundByChimneyStatus(chimneyStatus)
            viewModel.refreshButtonNextVisibility()
        }
        redChimneyContainer.setOnClickListener {
            chimneyStatus = RED
            updateChimneyStatusBackgroundByChimneyStatus(chimneyStatus)
            viewModel.refreshButtonNextVisibility()
        }
        greenAdditionContainer.setOnClickListener {
            additonStatus = GREEN
            updateAdditionStatusBackgroundByAdditionStatus(additonStatus)
            viewModel.refreshButtonNextVisibility()
        }
        yellowAdditionContainer.setOnClickListener {
            additonStatus = YELLOW
            updateAdditionStatusBackgroundByAdditionStatus(additonStatus)
            viewModel.refreshButtonNextVisibility()
        }
        redAdditionContainer.setOnClickListener {
            additonStatus = RED
            updateAdditionStatusBackgroundByAdditionStatus(additonStatus)
            viewModel.refreshButtonNextVisibility()
        }
        btnNext.setOnClickListener {
            apartmentId?.let { _apartmentId ->
                status = if (
                    boilerStatus.equals(RED, true) ||
                    gasStatus.equals(RED, true) ||
                    chimneyStatus.equals(RED, true)
                ) {
                    RED
                } else if (
                    boilerStatus.equals(YELLOW, true) ||
                    gasStatus.equals(YELLOW, true) ||
                    chimneyStatus.equals(YELLOW, true)
                ) {
                    YELLOW
                } else if (
                    boilerStatus.equals(GREEN, true) &&
                    gasStatus.equals(GREEN, true) &&
                    chimneyStatus.equals(GREEN, true)
                ) {
                    GREEN
                } else YELLOW
                val requestData =
                    CheckRequestData(
                        _apartmentId,
                        boilerStatus,
                        chimneyStatus,
                        gasStatus,
                        status,
                        additonStatus
                    )
                viewModel.check(requestData)
            }
        }
        setFragmentResultListener("REPORT_DIALOG_TO_TUTOR_STUDENT_STATUS_FRAGMENT") { requestKey, bundle ->
            popBackStack()
        }
    }

    override fun TutorStudentStatusBinding.setObservables() {
        viewModel.studentFlow.onEach {
            when (it) {
                WrappedResponse.Loading -> {}
                is WrappedResponse.Error -> toastMessage(it.message)
                is WrappedResponse.Success -> {
                    if (it.data.data.isNotEmpty()) {
                        val student = it.data.data.first()

                        additionContainer.isVisible = student.additionImage.url.isNotBlank()
                        studentId = student.studentId
                        apartmentId = student._id
                        boilerStatus = when (student.boilerImage.status) {
                            GREEN -> GREEN
                            YELLOW -> YELLOW
                            RED -> RED
                            else -> {
                                EMPTY_STRING
                            }
                        }
                        gasStatus = when (student.gazStove.status) {
                            GREEN -> GREEN
                            YELLOW -> YELLOW
                            RED -> RED
                            else -> {
                                EMPTY_STRING
                            }
                        }
                        chimneyStatus = when (student.chimney.status) {
                            GREEN -> GREEN
                            YELLOW -> YELLOW
                            RED -> RED
                            else -> {
                                EMPTY_STRING
                            }
                        }
                        additonStatus = when (student.additionImage.status) {
                            GREEN -> GREEN
                            YELLOW -> YELLOW
                            RED -> RED
                            else -> {
                                EMPTY_STRING
                            }
                        }
                        tvBoilerCreatedDate.text = formatDate(student.createdAt)
                        tvGasCreatedDate.text = formatDate(student.createdAt)
                        tvChimneyCreatedDate.text = formatDate(student.createdAt)
                        tvAdditionCreatedDate.text = formatDate(student.createdAt)
                        Glide
                            .with(requireContext())
                            .load("${BASE_URL.removeSuffix("/")}${student.boilerImage.url}")
                            .into(ivBoiler)
                        Glide
                            .with(requireContext())
                            .load("${BASE_URL.removeSuffix("/")}${student.gazStove.url}")
                            .into(ivGas)
                        Glide
                            .with(requireContext())
                            .load("${BASE_URL.removeSuffix("/")}${student.chimney.url}")
                            .into(ivChimney)
                        Glide
                            .with(requireContext())
                            .load("${BASE_URL.removeSuffix("/")}${student.additionImage.url}")
                            .into(ivAddition)
                        updateBoilerStatusBackgroundByBoilerStatus(boilerStatus)
                        updateGasStatusBackgroundByGasStatus(gasStatus)
                        updateChimneyStatusBackgroundByChimneyStatus(chimneyStatus)
                        updateAdditionStatusBackgroundByAdditionStatus(additonStatus)
                        viewModel.refreshButtonNextVisibility()
                    }
                }
            }
        }.launchIn(lifecycleScope)
        viewModel.checkFlow.onEach {
            when (it) {
                WrappedResponse.Loading -> {}
                is WrappedResponse.Error -> toastMessage(it.message)
                is WrappedResponse.Success -> {
                    if (it.data.status.equals("success", true)) {
                        apartmentId?.let { _apartmentId ->
                            val message = when (status) {
                                GREEN -> "Tabriklaymiz, siz \"Yashil\" toifadasiz"
                                YELLOW -> "Tabriklaymiz, siz \"Sariq\" toifadasiz"
                                RED -> "Afsuski, siz \"Qizil\" toifadasiz"
                                else -> return@let
                            }
                            val requestData = PushNotificationRequestData(
                                _apartmentId,
                                message,
                                status,
                                studentId
                            )
                            viewModel.pushNotification(requestData)
                        }
                    }
                }
            }
        }.launchIn(lifecycleScope)
        viewModel.pushNotificationFlow.onEach {
            when (it) {
                WrappedResponse.Loading -> {}
                is WrappedResponse.Error -> toastMessage(it.message)
                is WrappedResponse.Success -> {
                    if (it.data.status.equals("success", true)) {
                        toastMessage("Student muvaffaqiyatli yangilandi")
                        popBackStack()
                    }
                }
            }
        }.launchIn(lifecycleScope)
        viewModel.buttonNextVisibilityFlow.onEach {
            if (boilerStatus.isNotBlank() && gasStatus.isNotBlank() && chimneyStatus.isNotBlank() && additonStatus.isNotBlank()) {
                activeNextButton()
            }
        }.launchIn(lifecycleScope)
    }

    private fun TutorStudentStatusBinding.updateBoilerStatusBackgroundByBoilerStatus(boilerStatus: String) {
        greenBoilerContainer.setBackgroundResource(R.drawable.unselected_status_background)
        yellowBoilerContainer.setBackgroundResource(R.drawable.unselected_status_background)
        redBoilerContainer.setBackgroundResource(R.drawable.unselected_status_background)
        when (boilerStatus) {
            GREEN -> greenBoilerContainer.setBackgroundResource(R.drawable.green_selected_status_background)
            YELLOW -> yellowBoilerContainer.setBackgroundResource(R.drawable.yellow_selected_status_background)
            RED -> redBoilerContainer.setBackgroundResource(R.drawable.red_selected_status_background)
            else -> {}
        }
    }

    private fun TutorStudentStatusBinding.updateGasStatusBackgroundByGasStatus(gasStatus: String) {
        greenGasContainer.setBackgroundResource(R.drawable.unselected_status_background)
        yellowGasContainer.setBackgroundResource(R.drawable.unselected_status_background)
        redGasContainer.setBackgroundResource(R.drawable.unselected_status_background)
        when (gasStatus) {
            GREEN -> greenGasContainer.setBackgroundResource(R.drawable.green_selected_status_background)
            YELLOW -> yellowGasContainer.setBackgroundResource(R.drawable.yellow_selected_status_background)
            RED -> redGasContainer.setBackgroundResource(R.drawable.red_selected_status_background)
            else -> {}
        }
    }

    private fun TutorStudentStatusBinding.updateChimneyStatusBackgroundByChimneyStatus(chimneyStatus: String) {
        greenChimneyContainer.setBackgroundResource(R.drawable.unselected_status_background)
        yellowChimneyContainer.setBackgroundResource(R.drawable.unselected_status_background)
        redChimneyContainer.setBackgroundResource(R.drawable.unselected_status_background)
        when (chimneyStatus) {
            GREEN -> greenChimneyContainer.setBackgroundResource(R.drawable.green_selected_status_background)
            YELLOW -> yellowChimneyContainer.setBackgroundResource(R.drawable.yellow_selected_status_background)
            RED -> redChimneyContainer.setBackgroundResource(R.drawable.red_selected_status_background)
            else -> {}
        }
    }

    private fun TutorStudentStatusBinding.updateAdditionStatusBackgroundByAdditionStatus(
        additonStatus: String
    ) {
        greenAdditionContainer.setBackgroundResource(R.drawable.unselected_status_background)
        yellowAdditionContainer.setBackgroundResource(R.drawable.unselected_status_background)
        redAdditionContainer.setBackgroundResource(R.drawable.unselected_status_background)
        when (additonStatus) {
            GREEN -> greenAdditionContainer.setBackgroundResource(R.drawable.green_selected_status_background)
            YELLOW -> yellowAdditionContainer.setBackgroundResource(R.drawable.yellow_selected_status_background)
            RED -> redAdditionContainer.setBackgroundResource(R.drawable.red_selected_status_background)
            else -> {}
        }
    }

    private fun formatDate(isoDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC") // ISO format UTC da bo'ladi

        val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        val date: Date = inputFormat.parse(isoDate)!!
        return outputFormat.format(date)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun TutorStudentStatusBinding.activeNextButton() {
        btnNext.apply {
            btnNext.setOnTouchListener { _, _ -> false }
            alpha = 1.0f
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun TutorStudentStatusBinding.disactiveNextButton() {
        btnNext.apply {
            btnNext.setOnTouchListener { _, _ -> true }
            alpha = .7f
        }
    }

    companion object {
        private const val GREEN = "green"
        private const val YELLOW = "yellow"
        private const val RED = "red"
    }
}