package ir.vbile.app.taravaz.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Provides
    @Named(userSharedPreferences)
    fun provideUserSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences("SP_AUTH_TOKEN", MODE_PRIVATE)
}