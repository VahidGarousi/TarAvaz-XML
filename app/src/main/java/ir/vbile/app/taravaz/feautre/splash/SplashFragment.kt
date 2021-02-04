package ir.vbile.app.taravaz.feautre.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : TarAvazFragment(R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            delay(1500)
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }
}