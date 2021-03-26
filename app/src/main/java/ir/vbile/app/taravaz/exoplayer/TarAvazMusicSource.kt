package ir.vbile.app.taravaz.exoplayer

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import androidx.core.net.toUri
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import ir.vbile.app.taravaz.data.repo.SongRepository
import ir.vbile.app.taravaz.exoplayer.State.*
import ir.vbile.app.taravaz.extentions.asyncNetworkRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TarAvazMusicSource @Inject constructor(
    private val songRepository: SongRepository
) {
    var songs = emptyList<MediaMetadataCompat>()
    suspend fun fetchMediaData(artistId : Int?) = withContext(Dispatchers.IO) {
        state = STATE_INITIALIZING
        artistId?.let {
            songRepository.getAll(it)
                .asyncNetworkRequest()
                .subscribe { allSongs ->
                    songs = allSongs.map { song ->
                        MediaMetadataCompat.Builder()
                            .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, song.artist.name)
                            .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, song.mediaId)
                            .putString(MediaMetadataCompat.METADATA_KEY_TITLE, song.title)
                            .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, song.title)
                            .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, song.title)
                            .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, song.cover)
                            .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, song.songUrl)
                            .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, song.cover)
                            .build()
                    }
                }
        }
        state = STATE_INITIALIZED
    }

    fun asMediaSource(dataSourceFactory: DefaultDataSourceFactory): ConcatenatingMediaSource {
        val concatenatingMediaSource = ConcatenatingMediaSource()
        songs.forEach { song ->
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(
                    song.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI).toUri()
                )
            concatenatingMediaSource.addMediaSource(mediaSource)
        }
        return concatenatingMediaSource
    }

    fun asMediaItems() = songs.map { song ->
        val desc = MediaDescriptionCompat.Builder()
            .setMediaUri(song.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI).toUri())
            .setTitle(song.description.title)
            .setSubtitle(song.description.subtitle)
            .setMediaId(song.description.mediaId)
            .setIconUri(song.description.iconUri)
            .build()
        MediaBrowserCompat.MediaItem(desc, MediaBrowserCompat.MediaItem.FLAG_PLAYABLE)
    }.toMutableList()

    private val onReadyListeners = mutableListOf<(Boolean) -> Unit>()
    private var state: State = STATE_CREATED
        set(value) {
            if (value == STATE_INITIALIZED || value == STATE_ERROR) {
                synchronized(onReadyListeners) {
                    field = value
                    onReadyListeners.forEach { listener ->
                        listener(state == STATE_INITIALIZED)
                    }
                }
            } else {
                field = value
            }
        }


    fun whenReady(action: (Boolean) -> Unit): Boolean {
        return if (state == STATE_CREATED || state == STATE_INITIALIZING) {
            onReadyListeners += action
            false
        } else {
            action(state == STATE_INITIALIZED)
            true
        }
    }
}

enum class State {
    STATE_CREATED,
    STATE_INITIALIZING,
    STATE_INITIALIZED,
    STATE_ERROR
}