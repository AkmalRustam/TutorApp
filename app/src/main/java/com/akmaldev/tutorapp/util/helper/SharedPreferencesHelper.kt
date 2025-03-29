package com.akmaldev.tutorapp.util.helper

import android.content.SharedPreferences
import com.akmaldev.tutorapp.util.constant.AppConstants.ACCESS_TOKEN_KEY
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.constant.AppConstants.HAS_STUDENT_FRAGMENT_OPENED_KEY
import com.akmaldev.tutorapp.util.constant.AppConstants.QUESTION_NUMBER_KEY
import com.akmaldev.tutorapp.util.constant.AppConstants.STUDENT_FACULTY_NAME_KEY
import com.akmaldev.tutorapp.util.constant.AppConstants.STUDENT_FULL_NAME_KEY
import com.akmaldev.tutorapp.util.constant.AppConstants.STUDENT_GROUP_NAME_KEY
import com.akmaldev.tutorapp.util.constant.AppConstants.UNDEFINED_ID
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {
    /**
     * Common
     */
    var accessToken: String
        get() = sharedPreferences.getString(ACCESS_TOKEN_KEY, EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString(ACCESS_TOKEN_KEY, value).apply()
    /**
     * Tutor
     */
    var tutorName: String
        get() = sharedPreferences.getString("tutorFullName", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("tutorFullName", value).apply()
    var tutorPhoneNumber: String
        get() = sharedPreferences.getString("tutorPhoneNumber", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("tutorPhoneNumber", value).apply()
    var tutorImage: String
        get() = sharedPreferences.getString("tutorImage", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("tutorImage", value).apply()
    var tutorGroups: String
        get() = sharedPreferences.getString("tutorGroups", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("tutorGroups", value).apply()
    var tutorOrStudent: Int
        get() = sharedPreferences.getInt("tutorOrStudent", UNDEFINED_ID)
        set(value) = sharedPreferences.edit().putInt("tutorOrStudent", value)
            .apply()
    var tutorId: String
        get() = sharedPreferences.getString("tutorId", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("tutorId", value).apply()
    /**
     * Student
     */
    var studentFullName: String
        get() = sharedPreferences.getString(STUDENT_FULL_NAME_KEY, EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString(STUDENT_FULL_NAME_KEY, value).apply()
    var studentFirstName: String
        get() = sharedPreferences.getString("studentFirstName", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("studentFirstName", value).apply()
    var studentId: String
        get() = sharedPreferences.getString("studentId", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("studentId", value).apply()
    var studentImage: String
        get() = sharedPreferences.getString("studentImage", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("studentImage", value).apply()
    var studentRegion: String
        get() = sharedPreferences.getString("studentRegion", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("studentRegion", value).apply()
    var studentGroupName: String
        get() = sharedPreferences.getString(STUDENT_GROUP_NAME_KEY, EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString(STUDENT_GROUP_NAME_KEY, value).apply()
    var studentFacultyName: String
        get() = sharedPreferences.getString(STUDENT_FACULTY_NAME_KEY, EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString(STUDENT_FACULTY_NAME_KEY, value).apply()
    var studentGender: String
        get() = sharedPreferences.getString("studentGender", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("studentGender", value).apply()
    //
    var firstQuestionValue: String
        get() = sharedPreferences.getString("firstQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("firstQuestionValue", value).apply()
    //
    var secondQuestionValue: String
        get() = sharedPreferences.getString("secondQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("secondQuestionValue", value).apply()
    var secondQuestionRadioButtonId: Int
        get() = sharedPreferences.getInt("secondQuestionRadioButtonId", UNDEFINED_ID)
        set(value) = sharedPreferences.edit().putInt("secondQuestionRadioButtonId", value).apply()
    //
    var thirdQuestionValue: String
        get() = sharedPreferences.getString("thirdQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("thirdQuestionValue", value).apply()
    //
    var fourthQuestionValue: String
        get() = sharedPreferences.getString("fourthQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("fourthQuestionValue", value).apply()
    var fourthQuestionRadioButtonId: Int
        get() = sharedPreferences.getInt("fourthQuestionRadioButtonId", UNDEFINED_ID)
        set(value) = sharedPreferences.edit().putInt("fourthQuestionRadioButtonId", value).apply()
    var fourthQuestionLatitudeValue: String
        get() = sharedPreferences.getString("fourthQuestionLatitudeValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("fourthQuestionLatitudeValue", value).apply()
    var fourthQuestionLongitudeValue: String
        get() = sharedPreferences.getString("fourthQuestionLongitudeValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("fourthQuestionLongitudeValue", value).apply()
    //
    var fifthQuestionValue: String
        get() = sharedPreferences.getString("fifthQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("fifthQuestionValue", value).apply()
    var fifthQuestionRadioButtonId: Int
        get() = sharedPreferences.getInt("fifthQuestionRadioButtonId", UNDEFINED_ID)
        set(value) = sharedPreferences.edit().putInt("fifthQuestionRadioButtonId", value).apply()
    //
    var sixthQuestionValue: String
        get() = sharedPreferences.getString("sixthQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("sixthQuestionValue", value).apply()
    var sixthQuestionRadioButtonId: Int
        get() = sharedPreferences.getInt("sixthQuestionRadioButtonId", UNDEFINED_ID)
        set(value) = sharedPreferences.edit().putInt("sixthQuestionRadioButtonId", value).apply()
    //
    var seventhQuestionValue: String
        get() = sharedPreferences.getString("seventhQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("seventhQuestionValue", value).apply()
    var seventhQuestionRadioButtonId: Int
        get() = sharedPreferences.getInt("seventhQuestionRadioButtonId", UNDEFINED_ID)
        set(value) = sharedPreferences.edit().putInt("seventhQuestionRadioButtonId", value).apply()
    //
    var eighthQuestionValue: String
        get() = sharedPreferences.getString("eighthQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("eighthQuestionValue", value).apply()
    //
    var ninthQuestionValue: String
        get() = sharedPreferences.getString("ninthQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("ninthQuestionValue", value).apply()
    //
    var tenthQuestionValue: String
        get() = sharedPreferences.getString("tenthQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("tenthQuestionValue", value).apply()
    //
    var eleventhQuestionValue: String
        get() = sharedPreferences.getString("eleventhQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("eleventhQuestionValue", value).apply()
    //
    var twelfthQuestionValue: String
        get() = sharedPreferences.getString("twelfthQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("twelfthQuestionValue", value).apply()
    //
    var thirteenthQuestionValue: String
        get() = sharedPreferences.getString("thirteenthQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("thirteenthQuestionValue", value).apply()
    //
    var fourteenthQuestionValue: String
        get() = sharedPreferences.getString("fourteenthQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("fourteenthQuestionValue", value).apply()
    //
    var fifteenthQuestionValue: String
        get() = sharedPreferences.getString("fifteenthQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("fifteenthQuestionValue", value).apply()
    var sixteenthQuestionValue: String
        get() = sharedPreferences.getString("sixteenthQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("sixteenthQuestionValue", value).apply()
    var seventeenthQuestionValue: String
        get() = sharedPreferences.getString("seventeenthQuestionValue", EMPTY_STRING) ?: EMPTY_STRING
        set(value) = sharedPreferences.edit().putString("seventeenthQuestionValue", value).apply()
    var hasStudentFragmentOpened: Boolean
        get() = sharedPreferences.getBoolean(HAS_STUDENT_FRAGMENT_OPENED_KEY, false)
        set(value) = sharedPreferences.edit().putBoolean(HAS_STUDENT_FRAGMENT_OPENED_KEY, value)
            .apply()
    var hasFormFilled: Boolean
        get() = sharedPreferences.getBoolean("hasFormFilled", false)
        set(value) = sharedPreferences.edit().putBoolean("hasFormFilled", value)
            .apply()
    var questionNumber: Int
        get() = sharedPreferences.getInt(QUESTION_NUMBER_KEY, 1)
        set(value) = sharedPreferences.edit().putInt(QUESTION_NUMBER_KEY, value).apply()
}