package com.akmaldev.tutorapp.presentation.ui.profile

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentProfileBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.profile.viewmodel.impl.ProfileViewModelImpl
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.constant.AppConstants.UNDEFINED_ID
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun FragmentProfileBinding.setViews() {
        tvStudentName.text = sharedPreferencesHelper.studentFullName
        tvGroupName.apply {
            text = sharedPreferencesHelper.studentGroupName
            isSelected = true
        }
        tvFacultyName.apply {
            text = sharedPreferencesHelper.studentFacultyName
            isSelected = true
        }
        etRegion.setText(sharedPreferencesHelper.studentRegion)
        etGender.setText(sharedPreferencesHelper.studentGender)
        if (sharedPreferencesHelper.studentImage.isNotBlank()) {
            changeImageContainer.visibility = View.INVISIBLE
            ivProfileHat.visibility = View.INVISIBLE
            ivStudentImage.apply {
                visibility = View.VISIBLE
                Glide
                    .with(context)
                    .load(sharedPreferencesHelper.studentImage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(this)
            }
        }
    }

    override fun FragmentProfileBinding.setListeners() {
        ivLogout.setOnClickListener {
            sharedPreferencesHelper.tutorOrStudent = UNDEFINED_ID
            sharedPreferencesHelper.accessToken = EMPTY_STRING
            navigateTo(R.id.userTypeFragment)
        }
        ivBack.setOnClickListener {
            popBackStack()
        }
    }

    companion object {
        private const val TAG = "ProfileFragmentTag"
    }
}