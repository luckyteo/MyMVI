package com.luckyteo.mymvi.data

import com.luckyteo.mymvi.data.model.Repo
import com.luckyteo.mymvi.data.model.User
import com.luckyteo.mymvi.data.model.UserDetail

interface GithubRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUser(userId: String): Result<UserDetail?>
    suspend fun getRepos(userId: String): Result<List<Repo>>
}