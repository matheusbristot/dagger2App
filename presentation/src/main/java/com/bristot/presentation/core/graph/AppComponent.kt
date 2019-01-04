package com.bristot.presentation.core.graph

import android.content.Context
import com.bristot.presentation.provider.application.Dagger2Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    ApplicationModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: Dagger2Application)
}