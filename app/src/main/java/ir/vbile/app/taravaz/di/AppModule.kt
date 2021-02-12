package ir.vbile.app.taravaz.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import ir.vbile.app.taravaz.services.FrescoImageLoadingServiceImpl
import ir.vbile.app.taravaz.services.ImageLoadingService

@Module
@InstallIn(ActivityComponent::class)
object AppModule {
    @Provides
    @ActivityScoped
    fun provideImageLoadingService(
        impl: FrescoImageLoadingServiceImpl
    ): ImageLoadingService = impl
}
