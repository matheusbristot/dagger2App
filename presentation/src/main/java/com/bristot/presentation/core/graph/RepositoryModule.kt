package com.bristot.presentation.core.graph

import com.bristot.data.repository.DefaultUsersRepository
import com.bristot.domain.interactor.users.UsersRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindUsersRepository(defaultUsersRepository: DefaultUsersRepository): UsersRepository
}