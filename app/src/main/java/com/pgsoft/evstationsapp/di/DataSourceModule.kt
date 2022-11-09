package com.pgsoft.evstationsapp.di

import com.pgsoft.evstationsapp.data.datasources.location.LocationDataSource
import com.pgsoft.evstationsapp.data.datasources.location.LocationDataSourceFake
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindLocationDataSource(locationDataSource: LocationDataSourceFake): LocationDataSource
}