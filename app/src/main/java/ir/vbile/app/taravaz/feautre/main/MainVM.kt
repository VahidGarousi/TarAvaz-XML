package ir.vbile.app.taravaz.feautre.main

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.taravaz.App
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.Constants.MEDIA_ROOT_ID
import ir.vbile.app.taravaz.common.DataFactory
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.data.Song
import ir.vbile.app.taravaz.exoplayer.MusicServiceConnection
import ir.vbile.app.taravaz.exoplayer.isPlayEnabled
import ir.vbile.app.taravaz.exoplayer.isPlaying
import ir.vbile.app.taravaz.exoplayer.isPrepared
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection
) : TarAvazViewModel() {
    private val _mediaItems = MutableLiveData<List<Song>>()
    val mediaItems: LiveData<List<Song>> = _mediaItems
    val isConnected = musicServiceConnection.isConnected
    val networkError = musicServiceConnection.networkError
    val playbackState = musicServiceConnection.playbackState
    val curPlayingSong = musicServiceConnection.curPlayingSong

    init {
        _mediaItems.postValue(null)
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
                            Song(
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
                        _mediaItems.postValue(items)
                    }
                }
            })
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


    fun playOrToggleSong(mediaItem: Song, toggle: Boolean = false) {
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