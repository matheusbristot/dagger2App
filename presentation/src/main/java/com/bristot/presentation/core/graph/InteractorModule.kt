package com.bristot.presentation.core.graph

import com.bristot.domain.interactor.users.GetUsers
import com.bristot.domain.interactor.users.UsersRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideGetUsers(usersRepository: UsersRepository) = GetUsers(usersRepository)
}