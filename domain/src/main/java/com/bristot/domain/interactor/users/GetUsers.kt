package com.bristot.domain.interactor.users

class GetUsers(private val usersRepository: UsersRepository) {

    fun execute(gender: String) = usersRepository.getUsersByGender(gender)
}