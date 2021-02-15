package ir.vbile.app.taravaz.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import ir.vbile.app.taravaz.data.repo.source.*
import ir.vbile.app.taravaz.data.repo.source.remote.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideBannerDataSource(
        impl: BannerRemoteDataSource
    ): BannerDataSource = impl


    @Provides
    fun provideArtistDataSource(
        impl: ArtistRemoteDataSource
    ): ArtistDataSource = impl

    @Provides
    fun provideAlbumDataSource(
        impl: AlbumRemoteDataSource
    ): AlbumDataSource = impl

    @Provides
    fun provideGenreDataSource(
        impl: GenreRemoteDataSource
    ): GenreDataSource = impl

    @Provides
    fun provideSongDataSource(
        impl: SongRemoteDataSource
    ): SongDataSource = impl
}