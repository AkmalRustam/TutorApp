package com.akmaldev.tutorapp.presentation.ui.tutor

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentTutorBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.tutor.viewmodel.impl.TutorViewModelImpl
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.toastMessage
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class TutorFragment : BaseFragment<FragmentTutorBinding>(FragmentTutorBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private val viewModel: TutorViewModelImpl by viewModels()

    override fun FragmentTutorBinding.loadDatasFromNetwork() {
        viewModel.getTutorStatistics()
    }

    override fun FragmentTutorBinding.setObservables() {
        viewModel.statisticsFlow.onEach {
            when (it) {
                WrappedResponse.Loading -> {
                    pieChartView.visibility = View.INVISIBLE
                    progressBar.visibility = View.VISIBLE
                }

                is WrappedResponse.Error -> {
                    toastMessage(it.message)
                    pieChartView.visibility = View.VISIBLE
                    progressBar.visibility = View.INVISIBLE
                }

                is WrappedResponse.Success -> {
                    try {
                        val statistics = it.data.statistics
                        val redTotal = statistics.red.total
                        val redPercent = statistics.red.percent
                        val greenTotal = statistics.green.total
                        val greenPercent = statistics.green.percent
                        val yellowTotal = statistics.yellow.total
                        val yellowPercent = statistics.yellow.percent
                        val blueTotal = statistics.blue.total
                        val bluePercent = statistics.blue.percent
                        var showLabel = false

                        progressBar.visibility = View.INVISIBLE
                        pieChartView.apply {
                            visibility = View.VISIBLE
                            setSegmentValues(
                                getSegmentValues(redTotal, greenTotal, yellowTotal, blueTotal),
                                getSegmentColors(redTotal, greenTotal, yellowTotal, blueTotal)
                            )
                            setOnClickListener {
                                if (showLabel) {
                                    showLabel = false
                                    setShowLabels(showLabel)
                                } else {
                                    showLabel = true
                                    setShowLabels(showLabel)
                                }
                            }
                        }
                        tvGreenPercent.text = roundPercentage(greenPercent)
                        tvYellowPercent.text = roundPercentage(yellowPercent)
                        tvRedPercent.text = roundPercentage(redPercent)
                        tvBluePercent.text = roundPercentage(bluePercent)
                    } catch (e: Exception) {
                        when(e) {
                            is NullPointerException -> {
                                progressBar.visibility = View.INVISIBLE
                                toastMessage("Talabalar mavjud emas")
                            }
                        }
                        Log.d(TAG, "exception: $e")
                    }
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun getSegmentValues(
        redTotal: Int,
        greenTotal: Int,
        yellowTotal: Int,
        blueTotal: Int,
    ): List<Int> {
        val tempList = mutableListOf<Int>()
        tempList.clear()
        if (redTotal > 0) tempList.add(redTotal)
        if (greenTotal > 0) tempList.add(greenTotal)
        if (yellowTotal > 0) tempList.add(yellowTotal)
        if (blueTotal > 0) tempList.add(blueTotal)
        return tempList
    }

    private fun getSegmentColors(
        redTotal: Int,
        greenTotal: Int,
        yellowTotal: Int,
        blueTotal: Int,
    ): List<Int> {
        val tempList = mutableListOf<Int>()
        tempList.clear()
        if (redTotal > 0) tempList.add(requireContext().getColor(R.color.red))
        if (greenTotal > 0) tempList.add(requireContext().getColor(R.color.green))
        if (yellowTotal > 0) tempList.add(requireContext().getColor(R.color.orange))
        if (blueTotal > 0) tempList.add(requireContext().getColor(R.color.blue))
        return tempList
    }

    override fun FragmentTutorBinding.setValues() {
        sharedPreferencesHelper.tutorOrStudent = 2
    }

    @SuppressLint("SetTextI18n")
    override fun FragmentTutorBinding.setViews() {
        tvUserName.apply {
            text = "Salom, ${sharedPreferencesHelper.tutorName}"
            isSelected = true
        }
        if (sharedPreferencesHelper.tutorImage.isNotBlank()) Glide.with(requireContext())
            .load(sharedPreferencesHelper.tutorImage).centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(civUserImage)
    }

    override fun FragmentTutorBinding.setListeners() {
        btnStudentsAndAppointed.setOnClickListener {
            navigateTo(R.id.action_tutorFragment_to_studentTypeFragment)
        }
        mainContainer.setOnClickListener {
            navigateTo(R.id.action_tutorFragment_to_studentsAndGroups)
        }
        ivProfile.setOnClickListener {
            navigateTo(R.id.action_tutorFragment_to_tutorNotificationFragment)
        }
        ivMore.setOnClickListener {
            navigateTo(R.id.action_tutorFragment_to_tutorProfileFragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
        greenContainer.setOnClickListener {
            val direction = TutorFragmentDirections.actionTutorFragmentToStudentStatusFragment(GREEN)
            navigateTo(direction)
        }
        yellowContainer.setOnClickListener {
            val direction = TutorFragmentDirections.actionTutorFragmentToStudentStatusFragment(
                YELLOW)
            navigateTo(direction)
        }
        redContainer.setOnClickListener {
            val direction = TutorFragmentDirections.actionTutorFragmentToStudentStatusFragment(RED)
            navigateTo(direction)
        }
        blueContainer.setOnClickListener {
            val direction = TutorFragmentDirections.actionTutorFragmentToStudentStatusFragment(BLUE)
            navigateTo(direction)
        }
    }

    private fun roundPercentage(percentageString: String): String {
        val percentageDouble =
            percentageString.replace("%", "").toDoubleOrNull() ?: return "Noto'g'ri foiz formati"

        val roundedPercentage = if (percentageDouble - percentageDouble.toInt() >= 0.5) {
            percentageDouble.toInt() + 1
        } else {
            percentageDouble.toInt()
        }

        return "$roundedPercentage%"
    }

    companion object {
        private const val GREEN = "green"
        private const val YELLOW = "yellow"
        private const val RED = "red"
        private const val BLUE = "blue"
        private const val TAG = "TutorFragmentTag"
    }
}