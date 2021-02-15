package ir.vbile.app.taravaz.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.vbile.app.taravaz.exoplayer.MusicServiceConnection
import ir.vbile.app.taravaz.services.FrescoImageLoadingServiceImpl
import ir.vbile.app.taravaz.services.ImageLoadingService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMusicServiceConnection(
        @ApplicationContext context: Context,
    ) = MusicServiceConnection(context)

    @Provides
    @Singleton
    fun provideImageLoadingService(
        impl: FrescoImageLoadingServiceImpl
    ): ImageLoadingService = impl
}
