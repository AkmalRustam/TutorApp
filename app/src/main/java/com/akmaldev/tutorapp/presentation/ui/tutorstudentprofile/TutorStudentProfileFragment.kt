package com.akmaldev.tutorapp.presentation.ui.tutorstudentprofile

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import androidx.navigation.fragment.navArgs
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentTutorStudentProfileBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.extension.dpToPx
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.extension.toastMessage
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorStudentProfileFragment :
    BaseFragment<FragmentTutorStudentProfileBinding>(FragmentTutorStudentProfileBinding::inflate) {

    private val args: TutorStudentProfileFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun FragmentTutorStudentProfileBinding.setViews() {
        if (args.studentLat.isNotBlank() && args.studentLon.isNotBlank()) {
            mapContainer.visibility = View.VISIBLE
            val mapFragment = childFragmentManager.findFragmentById(
                R.id.map_view
            ) as? SupportMapFragment
            mapFragment?.getMapAsync { _googleMap ->
                val latLng = LatLng(args.studentLat.toDouble(), args.studentLon.toDouble())
                val zoomLevel = 17f
                val icon = when (args.studentStatus) {
                    GREEN -> getResizedBitmapDescriptor(R.drawable.ic_map_green_student_png, 36, 42)
                    YELLOW -> getResizedBitmapDescriptor(
                        R.drawable.ic_map_yellow_student_png,
                        36,
                        42
                    )

                    RED -> getResizedBitmapDescriptor(R.drawable.ic_map_red_student_png, 36, 42)
                    else -> getResizedBitmapDescriptor(R.drawable.ic_map_blue_student_png, 36, 42)
                }
                _googleMap.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title(null)
                        .icon(icon)
                )
                _googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
            }
        }
        Glide
            .with(requireContext())
            .load(args.studentImage)
            .centerCrop()
            .into(ivStudentImage)
        tvStudentName.text = "${args.studentName.take(1).uppercase()}${
            args.studentName.drop(1).lowercase()
        } ${args.studentSurname.take(1).uppercase()}${args.studentSurname.drop(1).lowercase()}"
        tvGroupName.text = args.studentGroup
        tvFacultyName.text = args.studentFaculty
        etRegion.setText(args.studentRegion)
        etGender.setText(args.studentGender)
        tvGroupName.isSelected = true
        tvFacultyName.isSelected = true
    }

    override fun FragmentTutorStudentProfileBinding.setListeners() {
        ivBack.setOnClickListener {
            popBackStack()
        }
        mapView.setOnClickListener {
            val latitude = args.studentLat.toDouble()
            val longitude = args.studentLon.toDouble()
            openGoogleMaps(latitude, longitude)
        }
    }

    private fun openGoogleMaps(latitude: Double, longitude: Double) {
        val uri = "geo:$latitude,$longitude?q=$latitude,$longitude"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps") // Faqat Google Maps bilan ochish
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            toastMessage("Google Maps ilovasi topilmadi")
        }
    }

    private fun getResizedBitmapDescriptor(
        resourceId: Int,
        dpWidth: Int,
        dpHeight: Int
    ): BitmapDescriptor {
        val imageBitmap = BitmapFactory.decodeResource(resources, resourceId)
        val resizedBitmap = Bitmap.createScaledBitmap(
            imageBitmap,
            dpWidth.dpToPx().toInt(),
            dpHeight.dpToPx().toInt(),
            false
        )
        return BitmapDescriptorFactory.fromBitmap(resizedBitmap)
    }

    companion object {
        private const val GREEN = "green"
        private const val YELLOW = "yellow"
        private const val RED = "red"
    }
}