package com.akmaldev.tutorapp.presentation.ui.question.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.akmaldev.tutorapp.presentation.ui.eighthquestion.EighthQuestionFragment
import com.akmaldev.tutorapp.presentation.ui.eleventhquestion.EleventhQuestionFragment
import com.akmaldev.tutorapp.presentation.ui.fifthquestion.FifthQuestionFragment
import com.akmaldev.tutorapp.presentation.ui.firstquestion.FirstQuestionFragment
import com.akmaldev.tutorapp.presentation.ui.fourthquestion.FourthQuestionFragment
import com.akmaldev.tutorapp.presentation.ui.ninthquestion.NinthQuestionFragment
import com.akmaldev.tutorapp.presentation.ui.secondquestion.SecondQuestionFragment
import com.akmaldev.tutorapp.presentation.ui.seventhquestion.SeventhQuestionFragment
import com.akmaldev.tutorapp.presentation.ui.sixthquestion.SixthQuestionFragment
import com.akmaldev.tutorapp.presentation.ui.tenthquestion.TenthQuestionFragment
import com.akmaldev.tutorapp.presentation.ui.thirdquestion.ThirdQuestionFragment

class
PagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = getFragments().size

    override fun createFragment(position: Int): Fragment {
        return getFragments()[position]
    }

    private fun getFragments(): List<Fragment> = listOf<Fragment>(
        FirstQuestionFragment(),
        SecondQuestionFragment(),
        ThirdQuestionFragment(),
        FourthQuestionFragment(),
        FifthQuestionFragment(),
        SixthQuestionFragment(),
        SeventhQuestionFragment(),
        EighthQuestionFragment(),
        NinthQuestionFragment(),
        TenthQuestionFragment(),
        EleventhQuestionFragment()
    )
}