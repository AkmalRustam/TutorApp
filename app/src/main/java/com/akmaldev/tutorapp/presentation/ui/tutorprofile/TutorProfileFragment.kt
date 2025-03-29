package com.akmaldev.tutorapp.presentation.ui.tutorprofile

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentTutorProfileBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.tutorprofile.viewmodel.impl.TutorProfileViewModelImpl
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.constant.AppConstants.UNDEFINED_ID
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.extension.toastMessage
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

@AndroidEntryPoint
class TutorProfileFragment :
    BaseFragment<FragmentTutorProfileBinding>(FragmentTutorProfileBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private val viewModel: TutorProfileViewModelImpl by viewModels()

    @SuppressLint("SetTextI18n")
    override fun FragmentTutorProfileBinding.setViews() {
        tvTutorName.text = "${
            sharedPreferencesHelper.tutorName.take(1).uppercase()
        }${sharedPreferencesHelper.tutorName.drop(1).lowercase()}"
        tvTutorPhoneNumber.text = sharedPreferencesHelper.tutorPhoneNumber
        if (sharedPreferencesHelper.tutorImage.isNotBlank()) {
            changeImageContainer.visibility = View.INVISIBLE
            ivProfileHat.visibility = View.INVISIBLE
            Glide
                .with(requireContext())
                .load(sharedPreferencesHelper.tutorImage)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivStudentImage)
        }
    }

    override fun FragmentTutorProfileBinding.setListeners() {
        ivBack.setOnClickListener {
            popBackStack()
        }
        ivLogout.setOnClickListener {
            sharedPreferencesHelper.tutorOrStudent = UNDEFINED_ID
            sharedPreferencesHelper.accessToken = EMPTY_STRING
            navigateTo(R.id.userTypeFragment)
        }
        changePasswordContainer.setOnClickListener {
            navigateTo(R.id.action_tutorProfileFragment_to_tutorChangePassword)
        }
        ivProfileHat.setOnClickListener {
            val direction =
                TutorProfileFragmentDirections.actionTutorProfileFragmentToCameraFragment(
                    false,
                    true,
                    false,
                    false,
                    false
                )
            navigateTo(direction)
        }
        changeImageContainer.setOnClickListener {
            val direction =
                TutorProfileFragmentDirections.actionTutorProfileFragmentToCameraFragment(
                    false,
                    true,
                    false,
                    false,
                    false
                )
            navigateTo(direction)
        }
        ivStudentImage.setOnClickListener {
            val direction =
                TutorProfileFragmentDirections.actionTutorProfileFragmentToCameraFragment(
                    false,
                    true,
                    false,
                    false,
                    false
                )
            navigateTo(direction)
        }
        setFragmentResultListener("CAPTURED_IMAGE_TO_TUTOR_PROFILE_FRAGMENT") { requestKey, bundle ->
            val imagePath = bundle.getString("profileImageUri", "").toUri().path ?: return@setFragmentResultListener
            val bitmap = getCorrectlyOrientedBitmap(requireContext(), imagePath)
            bitmap?.let {
                val file = convertBitmapToFile(requireContext(), it)
                val multipartImage = createMultipartBodyPart(file.absolutePath, "image")
                multipartImage?.let { profileImage ->
                    viewModel.updateProfileImage(profileImage)
                }
            }
        }
    }

    override fun FragmentTutorProfileBinding.loadDatasFromNetwork() {
        viewModel.tutorProfile()
    }

    override fun FragmentTutorProfileBinding.setObservables() {
        viewModel.profile.onEach {
            when (it) {
                WrappedResponse.Loading -> {}
                is WrappedResponse.Error -> toastMessage(it.message)
                is WrappedResponse.Success -> {
                    it.data.data.image?.let { image ->
                        changeImageContainer.visibility = View.INVISIBLE
                        ivProfileHat.visibility = View.INVISIBLE
                        ivStudentImage.apply {
                            Glide
                                .with(context)
                                .load(image)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(this)
                        }
                        sharedPreferencesHelper.tutorImage = image
                    }
                    tvTutorName.text = it.data.data.name
                    tvTutorPhoneNumber.text = it.data.data.phone
                }
            }
        }.launchIn(lifecycleScope)
        viewModel.updateProfileImageFlow.onEach {
            when(it) {
                WrappedResponse.Loading -> {}
                is WrappedResponse.Error -> toastMessage(it.message)
                is WrappedResponse.Success -> {
                    sharedPreferencesHelper.tutorImage = it.data.tutor.image
                    Glide
                        .with(requireContext())
                        .load(it.data.tutor.image)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivStudentImage)
                }
            }
        }.launchIn(lifecycleScope)
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

    private fun getCorrectlyOrientedBitmap(context: Context, imagePath: String): Bitmap? {
        val file = File(imagePath)
        if (!file.exists()) return null

        val bitmap = BitmapFactory.decodeStream(FileInputStream(file))
        val exif = ExifInterface(file.absolutePath)

        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        val rotation = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90f
            ExifInterface.ORIENTATION_ROTATE_180 -> 180f
            ExifInterface.ORIENTATION_ROTATE_270 -> 270f
            else -> 0f
        }

        return if (rotation != 0f) {
            val matrix = Matrix()
            matrix.postRotate(rotation)
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        } else {
            bitmap
        }
    }
    private fun convertBitmapToFile(context: Context, bitmap: Bitmap): File {
        val file = File(context.cacheDir, "profile_image.jpg")
        file.createNewFile()

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        val byteArray = bos.toByteArray()

        val fos = FileOutputStream(file)
        fos.write(byteArray)
        fos.flush()
        fos.close()

        return file
    }
}