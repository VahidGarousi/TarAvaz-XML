package ir.vbile.app.taravaz.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.vbile.app.taravaz.services.http.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class RestClientModule {

    @Provides
    internal fun provideStartupApiService(
        @TarAvazRetrofit retrofit: Retrofit
    ): StartupApi = retrofit.create(StartupApi::class.java)

    @Provides
    internal fun provideArtistApiService(
        @TarAvazRetrofit retrofit: Retrofit
    ): ArtistApi = retrofit.create(ArtistApi::class.java)

    @Provides
    internal fun provideAlbumApiService(
        @TarAvazRetrofit retrofit: Retrofit
    ): AlbumApi = retrofit.create(AlbumApi::class.java)

    @Provides
    internal fun provideGenreApiService(
        @TarAvazRetrofit retrofit: Retrofit
    ): GenreApi = retrofit.create(GenreApi::class.java)

    @Provides
    internal fun provideTrackApiService(
        @TarAvazRetrofit retrofit: Retrofit
    ): TrackApi = retrofit.create(TrackApi::class.java)


    @Provides
    @TarAvazRetrofit
    internal fun provideTarAvazRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(EndPoints.STAGE_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TarAvazRetrofit