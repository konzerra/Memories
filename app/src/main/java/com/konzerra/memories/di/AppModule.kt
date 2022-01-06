package com.konzerra.memories.di

import com.konzerra.memories.data.local.LocalDB
import com.konzerra.memories.data.repository.MemoryRepositoryImpl
import com.konzerra.memories.domain.repository.MemoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val realmVersion = 1L

    @Singleton
    @Provides
    fun providesRealmConfig(): RealmConfiguration =
        // 2.
        RealmConfiguration.Builder()
            .schemaVersion(realmVersion)
            .build()
    @Singleton
    @Provides
    fun providesLocalDB(realmConfiguration: RealmConfiguration): LocalDB {
        return LocalDB(realmConfiguration)
    }
    @Singleton
    @Provides
    fun providesMemoryRepository(localDB:LocalDB): MemoryRepository {
        return MemoryRepositoryImpl( localDB)
    }
}