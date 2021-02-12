package ir.vbile.app.taravaz

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp
import ir.vbile.app.taravaz.services.ImageLoadingService
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
        Timber.plant(Timber.DebugTree())
        Fresco.initialize(this)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        /*val myModules = module {
            single<ImageLoadingService> { FrescoImageLoadingServiceImpl() }
            factory {
                if (BuildConfig.DEMO_MODE)
                    DemoBannerRepositoryImpl()
                else
                    BannerRepositoryImpl(BannerRemoteDataSource(get()))
            }
            factory {
                if (BuildConfig.DEMO_MODE) {
                    DemoTrackRepositoryImpl()
                } else
                    TrackRepositoryImpl(TrackRemoteDataSource(get()))
            }
            factory {
                if (BuildConfig.DEMO_MODE)
                    DemoArtistRepository()
                else
                    ArtistRepositoryImpl(ArtistRemoteDataSource(get()))
            }
            factory {
                if (BuildConfig.DEMO_MODE)
                    DemoAlbumRepository()
                else
                    AlbumRepositoryImpl(AlbumRemoteDataSource(get()))
            }

            factory {
                if (BuildConfig.DEMO_MODE)
                    DemoGenreRepository()
                else
                    GenreRepositoryImpl(GenreRemoteDataSource(get()))
            }
            viewModel { HomeVM(get(), get()) }
            viewModel { (sort: Int) -> TrackVM(get(), sort) }
            viewModel { SearchVM(get(), get(), get()) }
            viewModel { ArtistVM(get()) }
            viewModel { GenreVM(get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(myModules)
        }*/
    }

    companion object {
        lateinit var appInstance: App
            private set
        val appContext: Context by lazy {
            appInstance.applicationContext
        }
    }
}