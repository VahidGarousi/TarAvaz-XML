package ir.vbile.app.taravaz.di

import android.content.Context
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ServiceScoped
import ir.vbile.app.taravaz.data.repo.SongRepository
import ir.vbile.app.taravaz.exoplayer.TarAvazMusicSource
import ir.vbile.app.taravaz.services.FrescoImageLoadingServiceImpl
import ir.vbile.app.taravaz.services.ImageLoadingService
import javax.inject.Singleton

@Module
@InstallIn(ServiceComponent::class)
object  ServiceModule {
    @Provides
    @ServiceScoped
    @TarAvazDataSourceServiceModule
    fun provideTarAvazDataSource(songRepository: SongRepository) =
        TarAvazMusicSource(songRepository)
    @Provides
    @ServiceScoped
    fun provideAudioAttributes() = AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    @Provides
    @ServiceScoped
    fun provideExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes
    ) = SimpleExoPlayer.Builder(context).build().apply {
        setAudioAttributes(audioAttributes, true)
        setHandleAudioBecomingNoisy(true)
    }

    @Provides
    @ServiceScoped
    fun provideDataSourceFactory(
        @ApplicationContext context: Context
    ) = DefaultDataSourceFactory(context, Util.getUserAgent(context, "TarAvaz App"))
}