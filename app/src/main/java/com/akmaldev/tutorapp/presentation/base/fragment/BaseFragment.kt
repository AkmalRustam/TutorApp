package com.akmaldev.tutorapp.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            setValues()
            setViews()
            setObservables()
            setListeners()
            loadDatasFromNetwork()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    open fun VB.loadDatasFromNetwork() {}

    open fun VB.setValues() {}

    open fun VB.setViews() {}

    open fun VB.setObservables() {}

    open fun VB.setListeners() {}
}