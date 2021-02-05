package ir.vbile.app.taravaz.feautre.track

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.view.TarAvazToolbar
import timber.log.Timber

class TracksFragment : TarAvazFragment(R.layout.fragment_track) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = requireActivity().findViewById<TarAvazToolbar>(R.id.toolbar)
    }
}