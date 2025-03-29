package com.akmaldev.tutorapp.presentation.ui.studentsandgroups

import androidx.recyclerview.widget.LinearLayoutManager
import com.akmaldev.tutorapp.data.model.response.auth._auth.TutorGroup
import com.akmaldev.tutorapp.databinding.FragmentStudentsAndGroupsBinding
import com.akmaldev.tutorapp.presentation.base.fragment.BaseFragment
import com.akmaldev.tutorapp.presentation.ui.studentsandgroups.adapter.StudentsAndGroupsAdapter
import com.akmaldev.tutorapp.util.extension.navigateTo
import com.akmaldev.tutorapp.util.extension.popBackStack
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StudentsAndGroupsFragment :
    BaseFragment<FragmentStudentsAndGroupsBinding>(FragmentStudentsAndGroupsBinding::inflate) {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    @Inject
    lateinit var gson: Gson

    private val studentsAndGroupsAdapter = StudentsAndGroupsAdapter()

    override fun FragmentStudentsAndGroupsBinding.setListeners() {
        ivBack.setOnClickListener {
            popBackStack()
        }
        studentsAndGroupsAdapter.setOnItemClickListener {
            val direction = StudentsAndGroupsFragmentDirections.actionStudentsAndGroupsToStudentsFragment(it.name, it.faculty)
            navigateTo(direction)
        }
    }

    override fun FragmentStudentsAndGroupsBinding.setViews() {
        val type = object : TypeToken<List<TutorGroup>>() {}.type
        val groups = gson.fromJson(sharedPreferencesHelper.tutorGroups, type) ?: emptyList<TutorGroup>()
        studentsAndGroupsAdapter.submitList(groups)
        recyclerViewGroups.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = studentsAndGroupsAdapter
        }
    }
}