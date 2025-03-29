package com.akmaldev.tutorapp.presentation.ui.camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentCameraBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.toastMessage
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CameraFragment : BaseFragment<FragmentCameraBinding>(FragmentCameraBinding::inflate) {

    private lateinit var cameraPermissionLauncher: ActivityResultLauncher<String>

    private var imageCapture: ImageCapture? = null
    private var camera: Camera? = null
    private var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private var flashMode = ImageCapture.FLASH_MODE_AUTO

    private val args: CameraFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (hasCameraPermission()) {
            startCamera()
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    override fun FragmentCameraBinding.setViews() {
        when (flashMode) {
            ImageCapture.FLASH_MODE_AUTO -> {
                flashMode = ImageCapture.FLASH_MODE_ON
                btnFlash.setImageResource(R.drawable.ic_flash_on)
            }

            ImageCapture.FLASH_MODE_ON -> {
                flashMode = ImageCapture.FLASH_MODE_OFF
                btnFlash.setImageResource(R.drawable.ic_flash_off)
            }

            ImageCapture.FLASH_MODE_OFF -> {
                flashMode = ImageCapture.FLASH_MODE_AUTO
                btnFlash.setImageResource(R.drawable.ic_flash_auto)
            }
        }
    }

    override fun FragmentCameraBinding.setListeners() {
        btnTakePhoto.setOnClickListener { takePhoto() }
        btnFlash.setOnClickListener {
            when (flashMode) {
                ImageCapture.FLASH_MODE_AUTO -> {
                    flashMode = ImageCapture.FLASH_MODE_ON
                    btnFlash.setImageResource(R.drawable.ic_flash_on)
                }

                ImageCapture.FLASH_MODE_ON -> {
                    flashMode = ImageCapture.FLASH_MODE_OFF
                    btnFlash.setImageResource(R.drawable.ic_flash_off)
                }

                ImageCapture.FLASH_MODE_OFF -> {
                    flashMode = ImageCapture.FLASH_MODE_AUTO
                    btnFlash.setImageResource(R.drawable.ic_flash_auto)
                }
            }
        }
        btnSwitchCamera.setOnClickListener { switchCamera() }
        cameraPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                startCamera()
            } else {
                toastMessage("Kameraga ruxsati rad etildi")
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.surfaceProvider = binding.previewView.surfaceProvider
                }

            imageCapture = ImageCapture.Builder()
                .setFlashMode(flashMode)
                .build()

            try {
                cameraProvider.unbindAll()
                camera = cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.d("CameraX", "Kamera ochishda xatolik: ", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun takePhoto() {
        val outputOptions = ImageCapture.OutputFileOptions.Builder(createImageFile()).build()
        imageCapture?.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val imageUri = outputFileResults.savedUri // Rasmni URI sifatida saqlaymiz
                    val direction =
                        CameraFragmentDirections.actionCameraFragmentToCapturedImageFragment(
                            imageUri.toString(),
                            args.fromHeatingEquipment,
                            args.fromTutorProfile,
                            args.fromStudentProfile,
                            args.fromChimney,
                            args.fromAdditional
                        )
                    navigateTo(direction)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("CameraFragment", "Photo capture failed: ${exception.message}")
                }
            }
        )
    }

    private fun switchCamera() {
        cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
            CameraSelector.DEFAULT_FRONT_CAMERA
        } else {
            CameraSelector.DEFAULT_BACK_CAMERA
        }
        startCamera()
    }

    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_$timestamp", ".jpg", storageDir)
    }

    private fun hasCameraPermission() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
}