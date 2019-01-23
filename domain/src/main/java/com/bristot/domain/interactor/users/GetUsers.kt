package com.bristot.domain.interactor.users

import javax.inject.Inject

class GetUsers @Inject constructor(private val usersRepository: UsersRepository) {

    fun execute(gender: String) = usersRepository.getUsersByGender(gender)
}