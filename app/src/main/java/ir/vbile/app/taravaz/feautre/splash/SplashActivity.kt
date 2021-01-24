package ir.vbile.app.taravaz.feautre.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazActivity

class SplashActivity : TarAvazActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}