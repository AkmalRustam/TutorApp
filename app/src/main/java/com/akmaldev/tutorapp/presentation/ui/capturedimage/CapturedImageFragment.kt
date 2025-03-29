package com.akmaldev.tutorapp.presentation.ui.capturedimage

import android.os.Bundle
import androidx.core.net.toUri
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.akmaldev.tutorapp.databinding.FragmentCapturedImageBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class CapturedImageFragment :
    BaseFragment<FragmentCapturedImageBinding>(FragmentCapturedImageBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private val args: CapturedImageFragmentArgs by navArgs()

    override fun FragmentCapturedImageBinding.setViews() {
        val imageUri = args.imageUri.toUri()
        val file = File(imageUri.path!!)
        val requestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("file", file.name, requestBody)

        ivCapturedImage.setImageURI(imageUri)
    }

    override fun FragmentCapturedImageBinding.setListeners() {
        btnSend.setOnClickListener {
            if (args.fromHeatingEquipment) {
                sharedPreferencesHelper.fourteenthQuestionValue = args.imageUri
                setFragmentResult("CAPTURED_IMAGE_TO_QUESTION_FRAGMENT", Bundle().apply { putInt("questionNumber", 14) })
            } else if (args.fromTutorProfile) {
                setFragmentResult(
                    "CAPTURED_IMAGE_TO_TUTOR_PROFILE_FRAGMENT",
                    Bundle().apply { this.putString("profileImageUri", args.imageUri) })
            } else if (args.fromStudentProfile) {
                setFragmentResult(
                    "CAPTURED_IMAGE_TO_PROFILE_FRAGMENT",
                    Bundle().apply { this.putString("profileImageUri", args.imageUri) })
            } else if (args.fromChimney) {
                sharedPreferencesHelper.sixteenthQuestionValue = args.imageUri
                setFragmentResult("CAPTURED_IMAGE_TO_QUESTION_FRAGMENT", Bundle().apply { putInt("questionNumber", 16) })
            } else if (args.fromAdditional) {
                sharedPreferencesHelper.seventeenthQuestionValue = args.imageUri
                setFragmentResult("CAPTURED_IMAGE_TO_QUESTION_FRAGMENT", Bundle().apply { putInt("questionNumber", 16) })
            } else {
                sharedPreferencesHelper.fifteenthQuestionValue = args.imageUri
                setFragmentResult("CAPTURED_IMAGE_TO_QUESTION_FRAGMENT", Bundle().apply { putInt("questionNumber", 14) })
            }
            popBackStack()
        }
    }
}