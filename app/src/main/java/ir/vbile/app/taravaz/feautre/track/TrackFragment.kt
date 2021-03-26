package ir.vbile.app.taravaz.feautre.track

import android.content.Intent
import android.os.Bundle
import android.support.v4.media.session.PlaybackStateCompat
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.View
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.Constants.ACTION_CHANGE_ARTIST_ID
import ir.vbile.app.taravaz.common.EXTRA_KEY_ID
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.exoplayer.MusicService
import ir.vbile.app.taravaz.exoplayer.isPlaying
import ir.vbile.app.taravaz.extentions.setVisibleOrGone
import ir.vbile.app.taravaz.services.ImageLoadingService
import ir.vbile.app.taravaz.view.cusom.ItemEventListener
import kotlinx.android.synthetic.main.fragment_track.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TrackFragment : TarAvazFragment<TrackVM>(
    R.layout.fragment_track,
    TrackVM::class
), ItemEventListener<Track, Int> {
    override fun getViewModelStoreOwner(): ViewModelStoreOwner = parentFragment ?: this
    private val args by navArgs<TrackFragmentArgs>()
    private val coupletAdapter = CoupletAdapter()
    @Inject
    lateinit var imageLoadingService: ImageLoadingService
    private var curPlayingSong: Track? = null
    private var shouldUpdateSeekBar = true
    private var playbackState: PlaybackStateCompat? = null
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomSheet()
        args.track.let {
            curPlayingSong = it
            updateTitleAndSogImage(it)
            sendCommandToService(ACTION_CHANGE_ARTIST_ID, args.track.artist.id)
        }
        btnPlayOrPause.setOnClickListener {
            curPlayingSong?.let {
                vm.playOrToggleSong(it)
            }
        }
        zoomView.addOnMaximizeChangeListener {
            when (it) {
                in 120..150 -> {
                    ivCover.setVisibleOrGone(false)
                    rvLyric.maxLines = 10
                    rvLyric.filters = arrayOf<InputFilter>(LengthFilter(250))
                }
            }
        }
        zoomView.addOnMinimizeChangeListener {
            when (it) {
                in 80..149 -> {
                    ivCover.setVisibleOrGone(true)
                    rvLyric.maxLines = 2
                    rvLyric.filters = arrayOf<InputFilter>(LengthFilter(75))
                }
            }
        }
        btnFullScreen.setOnClickListener {
            // FIXME: 3/26/2021 Fullscreen Lyric
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        rvSamples.setOnMoreEventListener {
            longToast(it.songUrl)
        }
        rvSamples.setOnItemEventListener(this)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        setUpCoupletAdapter()
        btnBookmark.setOnClickListener {
            
        }
    }

    private fun setUpCoupletAdapter() {
        rvLyrics.adapter = coupletAdapter
    }

    private fun setupBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(dialogLyric)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        val callback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) = Unit
        }
        bottomSheetBehavior.addBottomSheetCallback(callback)
        bottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_ALL
    }

    override fun subscribeToObservers() {
        vm.getTracks(args.track.artist.id)
        vm.tracks.observe(viewLifecycleOwner) { songs ->
            if (curPlayingSong == null && songs.isNotEmpty()) {
                curPlayingSong = songs[0]
                updateTitleAndSogImage(songs[0])
            }
            rvSamples.submitList(songs)
        }
        vm.curPlayerPosition.observe(viewLifecycleOwner) {
            if (shouldUpdateSeekBar) {
                seekBar.progress = it.toInt()
                setCurrentPlayerTimeToTextView(it)
            }
        }
        vm.curSongDuration.observe(viewLifecycleOwner) {
            seekBar.max = it.toInt()
            setCurrentPlayerDurationToTextView(it)
        }
        vm.playbackState.observe(viewLifecycleOwner) {
            playbackState = it
            btnPlayOrPause.setImageResource(if (playbackState?.isPlaying == true) R.drawable.ic_pause else R.drawable.ic_play)
            seekBar.progress = it?.position?.toInt() ?: 0
        }
        vm.couplets.observe(viewLifecycleOwner){
            coupletAdapter.submitList(it)
        }
    }

    private fun sendCommandToService(action: String, artistId: Int) {
        Intent(requireContext(), MusicService::class.java).also {
            it.action = action
            it.putExtra(EXTRA_KEY_ID, artistId)
            requireContext().startService(it)
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

    private fun updateTitleAndSogImage(track: Track) {
        val title = "${track.title} - ${track.title}"
        tvSongName.text = title
        tvArtistName.text = track.artist.name
        imageLoadingService.load(ivCover, track.cover)
        rvLyric.text = track.lyric
    }

    override fun onClick(item: Track, position: Int) {
        longToast(item.songUrl)
    }

    override fun onLongClick(item: Track, position: Int) {
        longToast(item.songUrl)
    }
}