package com.example.myapplication.di

import com.example.myapplication.data.locale.dao.CommentsDao
import com.example.myapplication.data.locale.dao.PostsDao
import com.example.myapplication.data.locale.dao.UserDao
import com.example.myapplication.data.network.repository.FeedCacheRepository
import com.example.myapplication.data.network.repository.FeedRepository
import com.example.myapplication.data.network.service.FeedService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoriesModule {

    @Singleton
    @Provides
    fun provideFeedRepository(
        @NetworkModule.FeedApi feedApi: FeedService,
        @LocaleModule.CommentDaoApi commentDaoApi: CommentsDao,
        @LocaleModule.PostsDaoApi postsDaoApi: PostsDao,
        @LocaleModule.UserDaoApi userDaoApi: UserDao
    ): FeedRepository {
        return FeedCacheRepository(
            feedApi,
            postsDaoApi,
            userDaoApi,
            commentDaoApi
        )
    }
}