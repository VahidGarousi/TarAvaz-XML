package ir.vbile.app.taravaz

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
        Timber.plant(Timber.DebugTree())
        Fresco.initialize(this)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
    }

    companion object {
        lateinit var appInstance: App
            private set
        val appContext: Context by lazy {
            appInstance.applicationContext
        }
    }
}