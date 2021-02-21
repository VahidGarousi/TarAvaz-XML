package ir.vbile.app.taravaz.feautre.track

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.taravaz.App
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.Constants.MEDIA_ROOT_ID
import ir.vbile.app.taravaz.common.Constants.UPDATE_PLAYER_POSITION_INTERVAL
import ir.vbile.app.taravaz.common.DataFactory
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.data.repo.SongRepository
import ir.vbile.app.taravaz.exoplayer.*
import ir.vbile.app.taravaz.extentions.asyncNetworkRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TrackVM @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection,
    private val songRepository: SongRepository
) : TarAvazViewModel() {
    var sort: Int = 1
    val selectedSortTitle: MutableLiveData<Int> = MutableLiveData()
    val sortTitles = arrayOf(
        R.string.sortLatest,
        R.string.sortPopular,
        R.string.sortPriceHighToLow,
        R.string.sortPriceLowToHigh
    )
    private val _tracks: MutableLiveData<List<Track>> = MutableLiveData()
    val tracks: LiveData<List<Track>> = _tracks

    fun getTracks(artistId : Int, sort: Int = 1) {
        viewModelScope.launch {
            songRepository.getAll(artistId)
                .asyncNetworkRequest()
                .subscribe { tracks ->
                    val mediaMetadataCompatList = tracks?.map(Track::toMediaItem)
                    _tracks.postValue(tracks)
                }
        }
    }

    fun onSelectedSortChangeByUseR(sort: Int) {
        this.sort = sort
        selectedSortTitle.value = sortTitles[sort]
        getTracks(sort)
    }

    val isConnected = musicServiceConnection.isConnected
    val networkError = musicServiceConnection.networkError
    val playbackState = musicServiceConnection.playbackState
    var curPlayingSong = musicServiceConnection.curPlayingSong

    private val _curSongDuration = MutableLiveData<Long>()
    val curSongDuration: LiveData<Long> = _curSongDuration

    private val _curPlayerPosition = MutableLiveData<Long>()
    val curPlayerPosition: LiveData<Long> = _curPlayerPosition

    init {
        getTracks(sort)
        updateCurrentPlayerPosition()
    }

    private fun updateCurrentPlayerPosition() {
        viewModelScope.launch {
            while (true) {
                val pos = playbackState.value?.currentPlaybackPosition
                if (curPlayerPosition.value != pos) {
                    _curPlayerPosition.postValue(pos)
                    _curSongDuration.postValue(MusicService.curSongDuration)
                }
                delay(UPDATE_PLAYER_POSITION_INTERVAL)
            }
        }
    }

    init {
        musicServiceConnection.subscribe(
            MEDIA_ROOT_ID,
            object : MediaBrowserCompat.SubscriptionCallback() {
                override fun onChildrenLoaded(
                    parentId: String,
                    children: MutableList<MediaBrowserCompat.MediaItem>
                ) {
                    super.onChildrenLoaded(parentId, children)
                    children.map {
                        val items = children.map {
                            Track(
                                mediaId = it.description.mediaId.toString(),
                                title = it.description.title.toString(),
                                lyric = it.description.subtitle.toString(),
                                number = it.description.mediaId?.toInt() ?: 1,
                                trackLength = 6500,
                                releaseDate = Calendar.getInstance(),
                                songUrl = it.description.mediaUri.toString(),
                                genre = App.appContext.getString(R.string.pop),
                                songWriter = App.appContext.getString(R.string.reza_bahram),
                                cover = it.description.iconUri.toString(),
                                artist = DataFactory.getSuggestedArtist()[1]
                            )
                        }
                        _tracks.postValue(items)
                    }
                }
            })
        Timber.i("")
    }

    fun skipToNextSong() {
        musicServiceConnection.transportControls.skipToNext()
    }

    fun skipToPreviousSong() {
        musicServiceConnection.transportControls.skipToPrevious()
    }

    fun seekTo(position: Long) {
        musicServiceConnection.transportControls.seekTo(position)
    }


    fun playOrToggleSong(mediaItem: Track, toggle: Boolean = false) {
        val isPrepared = playbackState.value?.isPrepared ?: false
        if (isPrepared && mediaItem.mediaId ==
            curPlayingSong.value?.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID)
        ) {
            playbackState.value?.let { playBackState ->
                when {
                    playBackState.isPlaying -> if (toggle) musicServiceConnection.transportControls.pause()
                    playBackState.isPlayEnabled -> musicServiceConnection.transportControls.play()
                    else -> Unit
                }
            }
        } else {
            musicServiceConnection.transportControls.playFromMediaId(mediaItem.mediaId, null)
        }
    }

    override fun onCleared() {
        super.onCleared()
        musicServiceConnection.unsubscribe(
            MEDIA_ROOT_ID,
            object : MediaBrowserCompat.SubscriptionCallback() {})
    }
}