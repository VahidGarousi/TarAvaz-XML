package ir.vbile.app.taravaz.feautre.artist.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.feautre.artist.ArtistVM
import ir.vbile.app.taravaz.view.cusom.ItemEventListener
import kotlinx.android.synthetic.main.fragment_artists.*
import org.koin.android.viewmodel.ext.android.viewModel

class ArtistsFragment : TarAvazFragment(R.layout.fragment_artists), ItemEventListener<Artist, Int> {
    val vm: ArtistVM by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rowSuggestedArtists.setOnItemEventListener(this)
        rowArtists.setOnItemEventListener(this)
        vm.artists.observe(viewLifecycleOwner) {
            rowSuggestedArtists.submitList(it)
            rowArtists.submitList(it)
        }
    }

    override fun onClick(item: Artist, position: Int) {
        val action = ArtistsFragmentDirections.actionArtistsFragmentToArtistFragment(item)
        findNavController().navigate(action)
    }

    override fun onLongClick(item: Artist, position: Int) {
    }

}