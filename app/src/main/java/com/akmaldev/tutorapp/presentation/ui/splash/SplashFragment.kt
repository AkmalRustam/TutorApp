package com.akmaldev.tutorapp.presentation.ui.splash

import androidx.lifecycle.lifecycleScope
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentSplashBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.constant.AppConstants.UNDEFINED_ID
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            delay(1500L)
            when(sharedPreferencesHelper.tutorOrStudent) {
                1 -> navigateTo(R.id.action_splashFragment_to_studentFragment)
                2 -> navigateTo(R.id.action_splashFragment_to_tutorFragment)
                UNDEFINED_ID -> navigateTo(R.id.action_splashFragment_to_userTypeFragment)
                else -> navigateTo(R.id.action_splashFragment_to_userTypeFragment)
            }
        }
    }
}
