package com.akmaldev.tutorapp.presentation.ui.question

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentQuestionBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.question.adapter.PagerAdapter
import com.akmaldev.tutorapp.presentation.ui.question.viewmodel.impl.QuestionViewModelImpl
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.constant.AppConstants.PHONE_NUMBER_PREFIX
import com.akmaldev.tutorapp.util.constant.AppConstants.UNDEFINED_ID
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.extension.toastMessage
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@AndroidEntryPoint
class QuestionFragment : BaseFragment<FragmentQuestionBinding>(FragmentQuestionBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private val args: QuestionFragmentArgs by navArgs()
    private val viewModel: QuestionViewModelImpl by viewModels()

    override fun FragmentQuestionBinding.setValues() {
    }

    override fun FragmentQuestionBinding.setViews() {
        binding.viewPager.apply {
            isUserInputEnabled = false
            adapter = PagerAdapter(requireActivity())
        }
        navigatePagerByQuestionNumber(args.questionNumber)
        updateQuestionChangeButtonsByPagerPosition(viewPager.currentItem)
    }

    override fun FragmentQuestionBinding.setObservables() {
        viewModel.createApartmentFlow.onEach {
            when (it) {
                WrappedResponse.Loading -> {}
                is WrappedResponse.Error -> {
                    toastMessage(it.message)
                    if (it.message.equals("Siz oldin ijara ma'lumotlarini kiritgansiz", true)) {
                        sharedPreferencesHelper.questionNumber = 17
                        popBackStack()
                    }
                }

                is WrappedResponse.Success -> {
                    navigateTo(R.id.finishFragment)
                }
            }
        }.launchIn(lifecycleScope)
    }

    override fun FragmentQuestionBinding.setListeners() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateQuestionChangeButtonsByPagerPosition(position)
            }

        })
        btnMainNext.setOnClickListener {
            viewPager.apply {
                setCurrentItem(currentItem.plus(1), false)
            }
        }
        btnPrevious.setOnClickListener {
            viewPager.apply {
                setCurrentItem(currentItem.plus(1), false)
            }
        }
        btnPreviousSecond.setOnClickListener {
            viewPager.apply {
                setCurrentItem(currentItem.minus(1), false)
            }
        }
        btnNext.setOnClickListener {
            viewPager.apply {
                setCurrentItem(currentItem.minus(1), false)
            }
        }
        setFragmentResultListener("CAPTURED_IMAGE_TO_QUESTION_FRAGMENT") { requestKey, bundle ->
            val numberQuestion = bundle.getInt("questionNumber", UNDEFINED_ID)
            binding.viewPager.apply {
                isUserInputEnabled = false
                adapter = PagerAdapter(requireActivity())
                when (numberQuestion) {
                    14 -> setCurrentItem(9, false)
                    16 -> setCurrentItem(10, false)
                }
            }
        }
        btnFinish.setOnClickListener {
            val studentId = sharedPreferencesHelper.studentId
            val studentPhoneNumber = "${PHONE_NUMBER_PREFIX}${
                sharedPreferencesHelper.firstQuestionValue.replace(
                    " ",
                    ""
                )
            }"
            val district = sharedPreferencesHelper.secondQuestionValue
            val fullAddress = sharedPreferencesHelper.thirdQuestionValue
            val smallDistrict = sharedPreferencesHelper.fourthQuestionValue
            val typeOfAppartment = sharedPreferencesHelper.fifthQuestionValue
            val contract = (sharedPreferencesHelper.sixthQuestionValue == "Ha").toString()
            val typeOfBoiler = sharedPreferencesHelper.seventhQuestionValue
            val priceAppartment = sharedPreferencesHelper.eighthQuestionValue
            val numberOfStudents = sharedPreferencesHelper.ninthQuestionValue
            val appartmentOwnerName = sharedPreferencesHelper.tenthQuestionValue
            val appartmentOwnerPhone = "${PHONE_NUMBER_PREFIX}${
                sharedPreferencesHelper.eleventhQuestionValue.replace(
                    " ",
                    ""
                )
            }"
            val appartmentNumber = sharedPreferencesHelper.twelfthQuestionValue
            val lat = sharedPreferencesHelper.fourthQuestionLatitudeValue
            val lon = sharedPreferencesHelper.fourthQuestionLongitudeValue
            val description = sharedPreferencesHelper.thirteenthQuestionValue
            val boilerImage =
                sharedPreferencesHelper.fourteenthQuestionValue.toUri().path ?: EMPTY_STRING
            val gazStove =
                sharedPreferencesHelper.fifteenthQuestionValue.toUri().path ?: EMPTY_STRING
            val chimney =
                sharedPreferencesHelper.sixteenthQuestionValue.toUri().path ?: EMPTY_STRING
            val addition =
                sharedPreferencesHelper.seventeenthQuestionValue.toUri().path ?: EMPTY_STRING
            if (
                sharedPreferencesHelper.firstQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.secondQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.thirdQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.fourthQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.fourthQuestionLatitudeValue.isNotBlank() &&
                sharedPreferencesHelper.fourthQuestionLongitudeValue.isNotBlank() &&
                sharedPreferencesHelper.fifthQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.sixthQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.seventhQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.eighthQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.ninthQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.tenthQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.eleventhQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.twelfthQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.fourteenthQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.fifteenthQuestionValue.isNotBlank() &&
                sharedPreferencesHelper.sixteenthQuestionValue.isNotBlank()
            )
                viewModel.createApartment(
                    createRequestBody(studentId),
                    createRequestBody(studentPhoneNumber),
                    createRequestBody(district),
                    createRequestBody(fullAddress),
                    createRequestBody(smallDistrict),
                    createRequestBody(typeOfAppartment),
                    createRequestBody(contract),
                    createRequestBody(typeOfBoiler),
                    createRequestBody(priceAppartment),
                    createRequestBody(numberOfStudents),
                    createRequestBody(appartmentOwnerName),
                    createRequestBody(appartmentOwnerPhone),
                    createRequestBody(appartmentNumber),
                    createMultipartBodyPart(boilerImage, "boilerImage"),
                    createMultipartBodyPart(gazStove, "gazStove"),
                    createMultipartBodyPart(chimney, "chimney"),
                    createMultipartBodyPart(addition, "additionImage"),
                    createRequestBody(lat),
                    createRequestBody(lon),
                    createRequestBody(description)
                )
            else
                toastMessage("Formani to'liq to'ldiring")
        }
    }

    private fun FragmentQuestionBinding.navigatePagerByQuestionNumber(questionNumber: Int) {
        when (questionNumber) {
            1 -> viewPager.setCurrentItem(0, false)
            2 -> viewPager.setCurrentItem(1, false)
            3 -> viewPager.setCurrentItem(2, false)
            4 -> viewPager.setCurrentItem(3, false)
            5 -> viewPager.setCurrentItem(4, false)
            7 -> viewPager.setCurrentItem(5, false)
            8 -> viewPager.setCurrentItem(6, false)
            10 -> viewPager.setCurrentItem(7, false)
            12 -> viewPager.setCurrentItem(8, false)
            14 -> viewPager.setCurrentItem(9, false)
            16, 17 -> viewPager.setCurrentItem(10, false)
        }
    }

    private fun FragmentQuestionBinding.updateQuestionChangeButtonsByPagerPosition(position: Int) {
        when (position) {
            0 -> {
                btnMainNext.visibility = View.VISIBLE
                btnNextAndPreviousGroup.visibility = View.INVISIBLE
                finishLayout.visibility = View.INVISIBLE
            }

            10 -> {
                btnMainNext.visibility = View.INVISIBLE
                btnNextAndPreviousGroup.visibility = View.INVISIBLE
                finishLayout.visibility = View.VISIBLE
            }

            else -> {
                btnMainNext.visibility = View.INVISIBLE
                btnNextAndPreviousGroup.visibility = View.VISIBLE
                finishLayout.visibility = View.INVISIBLE
            }
        }
    }

    private fun createRequestBody(value: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), value)
    }

    private fun createMultipartBodyPart(filePath: String, key: String): MultipartBody.Part? {
        val file = File(filePath)
        if (!file.exists()) return null
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        val compressedFile = File(file.parent, "compressed_${file.name}")
        val outputStream = FileOutputStream(compressedFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream) // 75% sifatda siqish
        outputStream.flush()
        outputStream.close()
        return if (file.exists()) {
            val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), compressedFile)
            MultipartBody.Part.createFormData(key, file.name, requestFile)
        } else {
            null
        }
    }

    companion object {
        private const val TAG = "QuestionFragmentTag"
    }
}