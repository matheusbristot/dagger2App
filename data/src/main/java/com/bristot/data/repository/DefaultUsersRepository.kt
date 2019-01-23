package com.bristot.data.repository

import com.bristot.data.client.ApiClient
import com.bristot.data.entity.ApiUser
import com.bristot.domain.entity.User
import com.bristot.domain.interactor.users.UsersRepository
import io.reactivex.Single
import javax.inject.Inject

class DefaultUsersRepository @Inject constructor() : UsersRepository {

    override fun getUsersByGender(gender: String): Single<List<User>> {
        return ApiClient.getUsersByGender(gender)
                .map { users -> ApiUser.ApiUserToUserMapper.transformElements(users) }
    }
}