package com.akmaldev.tutorapp.di

import com.akmaldev.tutorapp.domain.usecase.auth.GetStudentLoginUseCase
import com.akmaldev.tutorapp.domain.usecase.auth.GetTutorLoginUseCase
import com.akmaldev.tutorapp.domain.usecase.auth.impl.GetStudentLoginUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.auth.impl.GetTutorLoginUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.main.ChangePasswordUseCase
import com.akmaldev.tutorapp.domain.usecase.main.CheckUseCase
import com.akmaldev.tutorapp.domain.usecase.main.CreateApartmentUseCase
import com.akmaldev.tutorapp.domain.usecase.main.GetNotificationsUseCase
import com.akmaldev.tutorapp.domain.usecase.main.GetStudentProfileUseCase
import com.akmaldev.tutorapp.domain.usecase.main.GetTutorNotificationsUseCase
import com.akmaldev.tutorapp.domain.usecase.main.GetTutorStatisticsUseCase
import com.akmaldev.tutorapp.domain.usecase.main.PushNotificationUseCase
import com.akmaldev.tutorapp.domain.usecase.main.ReportUseCase
import com.akmaldev.tutorapp.domain.usecase.main.StudentByStudentIdUseCase
import com.akmaldev.tutorapp.domain.usecase.main.TutorProfileUseCase
import com.akmaldev.tutorapp.domain.usecase.main.TutorUpdateProfileImageUseCase
import com.akmaldev.tutorapp.domain.usecase.main.impl.ChangePasswordUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.main.impl.CheckUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.main.impl.CreateApartmentUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.main.impl.GetNotificationsUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.main.impl.GetStudentProfileUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.main.impl.GetTutorNotificationsUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.main.impl.GetTutorStatisticsUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.main.impl.PushNotificationUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.main.impl.ReportUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.main.impl.StudentByStudentIdUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.main.impl.TutorProfileUseCaseImpl
import com.akmaldev.tutorapp.domain.usecase.main.impl.TutorUpdateProfileImageUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetStudentLoginUseCase(studentLoginUseCaseImpl: GetStudentLoginUseCaseImpl): GetStudentLoginUseCase

    @Binds
    fun bindGetTutorLoginUseCase(tutorLoginUseCaseImpl: GetTutorLoginUseCaseImpl): GetTutorLoginUseCase

    @Binds
    fun bindGetNotificationsUseCase(getNotificationsUseCaseImpl: GetNotificationsUseCaseImpl): GetNotificationsUseCase

    @Binds
    fun bindCreateApartmentUseCase(createApartmentUseCaseImpl: CreateApartmentUseCaseImpl): CreateApartmentUseCase

    @Binds
    fun bindGetStudentProfileUseCase(getStudentProfileUseCaseImpl: GetStudentProfileUseCaseImpl): GetStudentProfileUseCase

    @Binds
    fun bindGetTutorStatisticsUseCase(getTutorStatisticsUseCaseImpl: GetTutorStatisticsUseCaseImpl): GetTutorStatisticsUseCase

    @Binds
    fun bindChangePasswordUseCase(changePasswordUseCaseImpl: ChangePasswordUseCaseImpl): ChangePasswordUseCase

    @Binds
    fun bindTutorProfileUseCase(tutorProfileUseCaseImpl: TutorProfileUseCaseImpl): TutorProfileUseCase

    @Binds
    fun bindGetTutorNotificationsUseCase(getTutorNotificationsUseCaseImpl: GetTutorNotificationsUseCaseImpl): GetTutorNotificationsUseCase

    @Binds
    fun bindStudentByStudentIdUseCase(studentByStudentIdUseCaseImpl: StudentByStudentIdUseCaseImpl): StudentByStudentIdUseCase

    @Binds
    fun bindCheckUseCase(checkUseCaseImpl: CheckUseCaseImpl): CheckUseCase

    @Binds
    fun bindTutorUpdateProfileImageUseCase(tutorUpdateProfileImageUseCaseImpl: TutorUpdateProfileImageUseCaseImpl): TutorUpdateProfileImageUseCase

    @Binds
    fun bindPushNotificationUseCase(pushNotificationUseCaseImpl: PushNotificationUseCaseImpl): PushNotificationUseCase

    @Binds
    fun bindReportUseCase(reportUseCaseImpl: ReportUseCaseImpl): ReportUseCase
}

