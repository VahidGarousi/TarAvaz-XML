package ir.vbile.app.taravaz.feautre.player

import android.os.Bundle
import android.support.v4.media.session.PlaybackStateCompat
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.data.Song
import ir.vbile.app.taravaz.exoplayer.isPlaying
import ir.vbile.app.taravaz.exoplayer.toSong
import ir.vbile.app.taravaz.feautre.main.MainVM
import ir.vbile.app.taravaz.services.ImageLoadingService
import kotlinx.android.synthetic.main.fragment_player.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class PlayerFragment : TarAvazFragment<PlayerVM>(
    R.layout.fragment_player,
    PlayerVM::class
) {
    override fun getViewModelStoreOwner(): ViewModelStoreOwner = parentFragment ?: this
    private val mainViewModel: MainVM by viewModels()
    private val args by navArgs<PlayerFragmentArgs>()

    @Inject
    lateinit var imageLoadingService: ImageLoadingService
    private var curPlayingSong: Song? = null
    private var shouldUpdateSeekBar = true
    private var playbackState: PlaybackStateCompat? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.song?.let {
            curPlayingSong = it
            updateTitleAndSogImage(it)
        }
        btnPlayOrPause.setOnClickListener {
            curPlayingSong?.let {
                mainViewModel.playOrToggleSong(it, true)
            }
        }
        mainViewModel.mediaItems.observe(viewLifecycleOwner) { songs ->
            Timber.i("")
            if (curPlayingSong == null && songs.isNotEmpty()) {
                curPlayingSong = songs[0]
                updateTitleAndSogImage(songs[0])
            }
        }
        mainViewModel.curPlayingSong.observe(viewLifecycleOwner) {
            Timber.i("")
            if (it == null) return@observe
            if (it.toSong() != curPlayingSong) {
                curPlayingSong = it.toSong()
                mainViewModel.playOrToggleSong(curPlayingSong!!)
            } else
                curPlayingSong = it.toSong()
            updateTitleAndSogImage(curPlayingSong!!)
        }
        mainViewModel.playbackState.observe(viewLifecycleOwner) {
            Timber.i("")
            playbackState = it
            btnPlayOrPause.setImageResource(if (playbackState?.isPlaying == true) R.drawable.ic_pause else R.drawable.ic_play)
            sliderSeconds.value = it?.position?.toFloat() ?: 0F
        }
        vm.curPlayerPosition.observe(viewLifecycleOwner) {
            Timber.i("")
            if (shouldUpdateSeekBar) {
                sliderSeconds.value = it.toFloat()
                setCurrentPlayerTimeToTextView(it)
            }
        }
        vm.curSongDuration.observe(viewLifecycleOwner) {
            Timber.i("")
            sliderSeconds.valueTo = it.toFloat()
            setCurrentPlayerDurationToTextView(it)
        }
    }

    private fun setCurrentPlayerTimeToTextView(milliseconds: Long?) {
        val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
        tvCurTime.text = dateFormat.format(milliseconds)
    }

    private fun setCurrentPlayerDurationToTextView(milliseconds: Long?) {
        val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
        tvSongDuration.text = dateFormat.format(milliseconds)
    }

    private fun updateTitleAndSogImage(song: Song) {
        val title = "${song.title} - ${song.title}"
        tvSongName.text = title
        tvArtistName.text = song.artist.name
        imageLoadingService.load(ivCover, song.cover)
        rvLyric.text = song.lyric
    }
}