package com.akmaldev.tutorapp.util.constant

object ServiceConstants {
    /**
     *  Student Endpoints
     */
    const val STUDENT_SIGN = "student/sign"
    const val STUDENT_NOTIFICATION = "student/notification/67b96f96627f8e7fdcde97fe"
    const val APARTMENT_CREATE = "appartment/create"
    const val STUDENT_PROFILE = "student/profile"
    /**
     *  Tutor Endpoints
     */
    const val TUTOR_LOGIN = "tutor/login"
    const val TUTOR_STATISTICS = "appertment/statistics/for-tutor"
    const val TUTOR_GROUP_STUDENTS = "tutor/students-group/{groupName}"
    const val TUTOR_STUDENTS_BY_STATUS = "appartment/status/{status}"
    const val TUTOR_CHANGE_PASSWORD = "tutor/change-password"
    const val TUTOR_PROFILE = "tutor/profile"
    const val TUTOR_NOTIFICATIONS = "tutor/notification/{tutorId}"
    const val TUTOR_STUDENT_BY_STIDENT_ID = "appartment/new/{studentId}"
    const val TUTOR_CHECK = "appartment/check"
    const val TUTOR_EDIT_PROFILE = "tutor/profile"
    const val TUTOR_PUSH_NOTIFICATION = "notification/push"
    const val TUTOR_REPORT = "notification/report"
}