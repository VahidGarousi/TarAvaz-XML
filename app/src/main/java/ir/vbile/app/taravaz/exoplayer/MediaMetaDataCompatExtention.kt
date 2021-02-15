package ir.vbile.app.taravaz.exoplayer

import android.support.v4.media.MediaMetadataCompat
import ir.vbile.app.taravaz.App
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.DataFactory
import ir.vbile.app.taravaz.data.Song
import java.util.*

fun MediaMetadataCompat.toSong(): Song? {
    return description?.let {
        Song(
            mediaId = it.mediaId.toString(),
            title = it.title.toString(),
            lyric = it.subtitle.toString(),
            number = it.mediaId?.toInt() ?: 1,
            trackLength = 6500,
            releaseDate = Calendar.getInstance(),
            songUrl = it.mediaUri.toString(),
            genre = App.appContext.getString(R.string.pop),
            songWriter = App.appContext.getString(R.string.reza_bahram),
            cover = it.iconUri.toString(),
            artist = DataFactory.getSuggestedArtist()[1]
        )
    }
}