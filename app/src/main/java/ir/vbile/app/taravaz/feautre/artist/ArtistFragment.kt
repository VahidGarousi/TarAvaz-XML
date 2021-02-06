package ir.vbile.app.taravaz.feautre.artist

import android.os.Bundle
import android.view.View
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import kotlinx.android.synthetic.main.fragment_artist.*
import org.koin.android.viewmodel.ext.android.viewModel

class ArtistFragment : TarAvazFragment(R.layout.fragment_artist) {
    val vm: ArtistVM by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.artists.observe(viewLifecycleOwner) {
            rowSuggestedArtists.submitList(it)
            rowArtists.submitList(it)
        }
    }
}