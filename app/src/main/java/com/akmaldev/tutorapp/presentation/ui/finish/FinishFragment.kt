package com.akmaldev.tutorapp.presentation.ui.finish

import androidx.activity.OnBackPressedCallback
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.FragmentFinishBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FinishFragment : BaseFragment<FragmentFinishBinding>(FragmentFinishBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun FragmentFinishBinding.setValues() {
        sharedPreferencesHelper.questionNumber = 17
    }

    override fun FragmentFinishBinding.setListeners() {
        btnMain.setOnClickListener {
            navigateTo(R.id.studentFragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateTo(R.id.studentFragment)
            }
        })
    }
}