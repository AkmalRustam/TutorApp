package com.akmaldev.tutorapp.presentation.ui.report

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.data.model.request.main.tutorstudentstatus.PushNotificationRequestData
import com.akmaldev.tutorapp.databinding.DialogFragmentReportBinding
import com.akmaldev.tutorapp.presentation.ui.tutorstudentstatus.viewmodel.impl.TutorStudentStatusViewModelImpl
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.extension.toastMessage
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ReportDialogFragment : DialogFragment(R.layout.dialog_fragment_report) {

    private var _binding: DialogFragmentReportBinding? = null
    private var hasBoilerChecked: Boolean = false
    private var hasGasChecked: Boolean = false
    private var hasChimneyChecked: Boolean = false

    private val binding get() = _binding!!
    private val viewModel: TutorStudentStatusViewModelImpl by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DialogFragmentReportBinding.bind(view)
        with(binding) {
            setViews()
            setListeners()
            setObservables()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun DialogFragmentReportBinding.setListeners() {
        btnNext.setOnClickListener {
            val messages = StringBuilder()
            val parts = mutableListOf<String>()
            if (hasBoilerChecked) parts.add("kotyol")
            if (hasGasChecked) parts.add("gas")
            if (hasChimneyChecked) parts.add("kavasi")
            messages.append("Iltimos ")
            parts.forEach { part ->
                messages.append("$part, ")
            }
            messages.append("rasmnini qayta jonating")
            val requestData = PushNotificationRequestData(
                arguments?.getString("apartmentId") ?: return@setOnClickListener,
                fixText(messages.toString()),
                "blue",
                arguments?.getString("studentId") ?: return@setOnClickListener
            )
            viewModel.report(requestData)
        }
        cbBoiler.setOnCheckedChangeListener { compoundButton, checked ->
            hasBoilerChecked = checked
            viewModel.refreshButtonNextVisibility()
        }
        cbGas.setOnCheckedChangeListener { compoundButton, checked ->
            hasGasChecked = checked
            viewModel.refreshButtonNextVisibility()
        }
        cbChimney.setOnCheckedChangeListener { compoundButton, checked ->
            hasChimneyChecked = checked
            viewModel.refreshButtonNextVisibility()
        }
    }

    private fun DialogFragmentReportBinding.setViews() {
        disactiveNextButton()
        dialog?.let { _dialog ->
            _dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    private fun DialogFragmentReportBinding.setObservables() {
        viewModel.buttonNextVisibilityFlow.onEach {
            if (hasBoilerChecked || hasGasChecked || hasChimneyChecked) {
                activeNextButton()
            } else {
                disactiveNextButton()
            }
        }.launchIn(lifecycleScope)
        viewModel.reportFlow.onEach {
            when (it) {
                WrappedResponse.Loading -> {}
                is WrappedResponse.Error -> toastMessage(it.message)
                is WrappedResponse.Success -> {
                    if (it.data.status.equals("success", true)) {
                        toastMessage("Student rasmlarni qayta yuborishi uchun xabarnoma yuborildi")
                        setFragmentResult("REPORT_DIALOG_TO_TUTOR_STUDENT_STATUS_FRAGMENT", Bundle.EMPTY)
                        popBackStack()
                    }
                }
            }
        }.launchIn(lifecycleScope)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun DialogFragmentReportBinding.activeNextButton() {
        btnNext.apply {
            btnNext.setOnTouchListener { _, _ -> false }
            alpha = 1.0f
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun DialogFragmentReportBinding.disactiveNextButton() {
        btnNext.apply {
            btnNext.setOnTouchListener { _, _ -> true }
            alpha = .7f
        }
    }

    private fun fixText(text: String): String {
        return text.replace(Regex(",\\s+(?=rasmnini)"), " ")
    }
}
