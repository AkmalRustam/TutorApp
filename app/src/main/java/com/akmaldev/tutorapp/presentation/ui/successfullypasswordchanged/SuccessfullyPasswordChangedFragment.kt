package com.akmaldev.tutorapp.presentation.ui.successfullypasswordchanged

import com.akmaldev.tutorapp.databinding.FragmentSuccessfullyPasswordChangedBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.util.extension.popBackStack

class SuccessfullyPasswordChangedFragment :
    BaseFragment<FragmentSuccessfullyPasswordChangedBinding>(
        FragmentSuccessfullyPasswordChangedBinding::inflate
    ) {
    override fun FragmentSuccessfullyPasswordChangedBinding.setListeners() {
        btnNext.setOnClickListener {
            popBackStack()
        }
    }
}