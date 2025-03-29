package com.akmaldev.tutorapp.presentation.ui.finish

import com.akmaldev.tutorapp.databinding.FragmentFinishBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FinishFragment : BaseFragment<FragmentFinishBinding>(FragmentFinishBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun FragmentFinishBinding.setValues() {
        sharedPreferencesHelper.questionNumber = 15
    }

    override fun FragmentFinishBinding.setListeners() {
        btnMain.setOnClickListener {
            popBackStack()
        }
    }
}