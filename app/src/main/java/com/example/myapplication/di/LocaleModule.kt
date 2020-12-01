package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.locale.Database
import com.example.myapplication.data.locale.dao.CommentsDao
import com.example.myapplication.data.locale.dao.PostsDao
import com.example.myapplication.data.locale.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocaleModule {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class PostsDaoApi

    @Singleton
    @PostsDaoApi
    @Provides
    fun providePostsApi(
        database: Database
    ): PostsDao {
        return database.postsDao()
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class UserDaoApi

    @Singleton
    @UserDaoApi
    @Provides
    fun provideUserDaoAoi(
        database: Database
    ): UserDao {
        return database.userDao()
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CommentDaoApi

    @Singleton
    @CommentDaoApi
    @Provides
    fun provideCommentsDaoApi(
        database: Database
    ): CommentsDao {
        return database.commentsDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "ILDatabase"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


}