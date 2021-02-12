package ir.vbile.app.taravaz.feautre.player

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.feautre.track.TrackVM
import ir.vbile.app.taravaz.services.ImageLoadingService
import kotlinx.android.synthetic.main.fragment_player.*
import javax.inject.Inject

@AndroidEntryPoint
class PlayerFragment : TarAvazFragment<TrackVM>(
    R.layout.fragment_player,
    TrackVM::class
) {
    private val args by navArgs<PlayerFragmentArgs>()

    @Inject
    lateinit var imageLoadingService: ImageLoadingService
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