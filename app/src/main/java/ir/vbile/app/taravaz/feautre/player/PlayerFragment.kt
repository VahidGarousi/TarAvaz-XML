package ir.vbile.app.taravaz.feautre.player

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.services.ImageLoadingService
import kotlinx.android.synthetic.main.fragment_player.*
import org.koin.android.ext.android.inject

class PlayerFragment : TarAvazFragment(R.layout.fragment_player) {
    private val args by navArgs<PlayerFragmentArgs>()
    val imageLoadingService: ImageLoadingService by inject()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.track?.let {
            tvTrackName.text = it.title
            tvArtistName.text = it.artist.name
            imageLoadingService.load(ivCover, it.cover)
            rvLyric.text = it.lyric
        }
    }
}