package ir.vbile.app.taravaz.exoplayer

import android.support.v4.media.MediaMetadataCompat
import ir.vbile.app.taravaz.data.Track

fun Track.toMediaItem(): MediaMetadataCompat = MediaMetadataCompat.Builder()
    .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist.name)
    .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, mediaId)
    .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
    .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, title)
    .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, title)
    .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, cover)
    .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, songUrl)
    .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, cover)
    .build()