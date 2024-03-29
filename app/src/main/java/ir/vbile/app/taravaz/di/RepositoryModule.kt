package ir.vbile.app.taravaz.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import ir.vbile.app.taravaz.BuildConfig
import ir.vbile.app.taravaz.data.repo.*
import ir.vbile.app.taravaz.data.repo.demo.*
import ir.vbile.app.taravaz.data.repo.impl.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideBannerRepository(
        impl: BannerRepositoryImpl
    ): BannerRepository = if (!BuildConfig.DEMO_MODE) impl else DemoBannerRepositoryImpl()

    @Provides
    fun provideAlbumRepository(
        impl: AlbumRepositoryImpl
    ): AlbumRepository = if (!BuildConfig.DEMO_MODE) impl else DemoAlbumRepositoryImpl()


    @Provides
    fun provideArtistRepository(
        impl: ArtistRepositoryImpl
    ): ArtistRepository = if (!BuildConfig.DEMO_MODE) impl else DemoArtistRepositoryImpl()


    @Provides
    fun provideGenreRepository(
        impl: GenreRepositoryImpl
    ): GenreRepository = if (!BuildConfig.DEMO_MODE) impl else DemoGenreRepositoryImpl()

    @Provides
    fun provideSongRepository(
        impl: SongRepositoryImpl
    ): SongRepository = if (!BuildConfig.DEMO_MODE) impl else DemoSongRepositoryImpl()

}