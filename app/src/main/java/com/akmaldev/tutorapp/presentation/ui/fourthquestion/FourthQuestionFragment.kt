package com.akmaldev.tutorapp.presentation.ui.fourthquestion

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.akmaldev.tutorapp.databinding.FragmentFourthQuestionBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.extension.toastMessage
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.google.android.material.radiobutton.MaterialRadioButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FourthQuestionFragment :
    BaseFragment<FragmentFourthQuestionBinding>(FragmentFourthQuestionBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var resolutionLauncher: ActivityResultLauncher<IntentSenderRequest>

    override fun onResume() {
        super.onResume()
        sharedPreferencesHelper.questionNumber = 4
        checkLocationPermission()
    }

    override fun FragmentFourthQuestionBinding.setValues() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun FragmentFourthQuestionBinding.setViews() {
        rgAddress.check(sharedPreferencesHelper.fourthQuestionRadioButtonId)
        etAddress.apply {
            isVisible = rgAddress.checkedRadioButtonId == rbTenth.id
            setText(sharedPreferencesHelper.fourthQuestionValue)
        }
    }

    override fun FragmentFourthQuestionBinding.setListeners() {
        locationPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    checkLocationSettings()
                } else {
                    toastMessage("Joylashuvga ruxsat berilmadi")
                }
            }
        resolutionLauncher = registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                getAndSaveUserLocation()
            } else {
                toastMessage("GPS yoqilmadi")
            }
        }
        rgAddress.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = binding.root.findViewById<MaterialRadioButton>(checkedId)
            val selectedText = selectedRadioButton.text.toString().trim()
            sharedPreferencesHelper.fourthQuestionValue = selectedText
            sharedPreferencesHelper.fourthQuestionRadioButtonId = selectedRadioButton.id
            etAddress.isVisible = checkedId == rbTenth.id
            if (checkedId == rbTenth.id) {
                etAddress.setText(EMPTY_STRING)
                sharedPreferencesHelper.fourthQuestionValue = EMPTY_STRING
            }
        }
        etAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString().trim()
                if (text.isNotBlank()) {
                    sharedPreferencesHelper.fourthQuestionValue = text
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            checkLocationSettings()
        } else {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getAndSaveUserLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude
                sharedPreferencesHelper.fourthQuestionLatitudeValue = latitude.toString()
                sharedPreferencesHelper.fourthQuestionLongitudeValue = longitude.toString()
            } else {
                getAndSaveUserLocation()
            }
        }.addOnFailureListener {
            toastMessage("Joylashuvni olishda xatolik")
        }
    }

    private fun checkLocationSettings() {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 1000
        ).build()

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .setAlwaysShow(true)

        val client = LocationServices.getSettingsClient(requireContext())
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener { response ->
            getAndSaveUserLocation()
        }.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    val intentSenderRequest =
                        IntentSenderRequest.Builder(exception.resolution).build()
                    resolutionLauncher.launch(intentSenderRequest)
                } catch (sendEx: IntentSender.SendIntentException) {
                    sendEx.printStackTrace()
                }
            }
        }
    }
}