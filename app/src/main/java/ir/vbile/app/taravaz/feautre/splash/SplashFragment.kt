package ir.vbile.app.taravaz.feautre.splash

import android.view.View
import androidx.navigation.findNavController
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : TarAvazFragment(R.layout.fragment_splash) {
    override fun setUpViews(view: View): View = view.apply {
        longToast(getString(R.string.app_name))
        GlobalScope.launch {
            delay(2000)
            findNavController().navigate(R.id.action_splashFragment_to_genreFragment)
        }
    }
}