package ir.vbile.app.taravaz

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.facebook.drawee.backends.pipeline.Fresco
import ir.vbile.app.taravaz.data.repo.demo.DemoAlbumRepository
import ir.vbile.app.taravaz.data.repo.demo.DemoArtistRepository
import ir.vbile.app.taravaz.data.repo.demo.DemoBannerRepositoryImpl
import ir.vbile.app.taravaz.data.repo.demo.DemoTrackRepositoryImpl
import ir.vbile.app.taravaz.data.repo.impl.AlbumRepositoryImpl
import ir.vbile.app.taravaz.data.repo.impl.ArtistRepositoryImpl
import ir.vbile.app.taravaz.data.repo.impl.BannerRepositoryImpl
import ir.vbile.app.taravaz.data.repo.impl.TrackRepositoryImpl
import ir.vbile.app.taravaz.data.repo.source.AlbumRemoteDataSource
import ir.vbile.app.taravaz.data.repo.source.ArtistRemoteDataSource
import ir.vbile.app.taravaz.data.repo.source.BannerRemoteDataSource
import ir.vbile.app.taravaz.data.repo.source.TrackRemoteDataSource
import ir.vbile.app.taravaz.feautre.home.HomeVM
import ir.vbile.app.taravaz.feautre.search.SearchVM
import ir.vbile.app.taravaz.feautre.track.TrackVM
import ir.vbile.app.taravaz.services.FrescoImageLoadingServiceImpl
import ir.vbile.app.taravaz.services.ImageLoadingService
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
        Timber.plant(Timber.DebugTree())
        Fresco.initialize(this)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        val myModules = module {
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
            viewModel { HomeVM(get(), get()) }
            viewModel { (sort: Int) -> TrackVM(get(), sort) }
            viewModel { SearchVM(get(), get(), get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }

    companion object {
        lateinit var appInstance: App
            private set
        val appContext: Context by lazy {
            appInstance.applicationContext
        }
    }
}