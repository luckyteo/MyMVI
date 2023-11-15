package com.luckyteo.mymvi.di

import com.luckyteo.mymvi.data.GithubRepository
import com.luckyteo.mymvi.data.GithubRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<GithubRepository> {
        GithubRepositoryImpl(
            githubApi = get()
        )
    }

}