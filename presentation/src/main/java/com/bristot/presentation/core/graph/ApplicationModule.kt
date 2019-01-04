package com.bristot.presentation.core.graph

import com.bristot.domain.Logger
import com.bristot.domain.Resources
import com.bristot.presentation.utils.LoggerImpl
import com.bristot.presentation.utils.ResourcesImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindResourcesProvider(resourcesImpl: ResourcesImpl): Resources

    @Binds
    @Singleton
    abstract fun bindLoggerProvider(loggerImpl: LoggerImpl): Logger
}