package ir.vbile.app.taravaz

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.facebook.drawee.backends.pipeline.Fresco
import ir.vbile.app.taravaz.data.repo.BannerRepository
import ir.vbile.app.taravaz.data.repo.demo.DemoBannerRepositoryImpl
import ir.vbile.app.taravaz.data.repo.impl.BannerRepositoryImpl
import ir.vbile.app.taravaz.data.repo.source.BannerRemoteDataSource
import ir.vbile.app.taravaz.feautre.home.HomeVM
import ir.vbile.app.taravaz.services.FrescoImageLoadingServiceImpl
import ir.vbile.app.taravaz.services.ImageLoadingService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Fresco.initialize(this)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        val myModules = module {
            single<ImageLoadingService> { FrescoImageLoadingServiceImpl() }
            factory <BannerRepository>{
                if (BuildConfig.DEMO_MODE)
                    DemoBannerRepositoryImpl()
                else
                    BannerRepositoryImpl(BannerRemoteDataSource(get()))
            }
            single { HomeVM(get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}