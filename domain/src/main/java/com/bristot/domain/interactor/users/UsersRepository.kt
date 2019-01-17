package com.bristot.domain.interactor.users

import com.bristot.domain.entity.User
import io.reactivex.Single

interface UsersRepository {
    fun getUsersByGender(gender: String): Single<List<User>>
}