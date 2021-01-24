package ir.vbile.app.taravaz.feautre.splash

import android.os.Bundle
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazActivity
import ir.vbile.app.taravaz.common.openActivity
import ir.vbile.app.taravaz.feautre.main.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class SplashActivity : TarAvazActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        GlobalScope.launch {
            delay(500)
            openActivity(MainActivity::class.java)
            finish()
        }

    }
}