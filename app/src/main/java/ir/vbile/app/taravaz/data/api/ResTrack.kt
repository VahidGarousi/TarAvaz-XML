package ir.vbile.app.taravaz.data.api

import ir.vbile.app.taravaz.common.DataFactory
import ir.vbile.app.taravaz.data.Song
import java.util.*

data class ResTrack(
    val id: String,
    val title: String,
    val lyric: String,
    val number: Int,
    val track_length: Int,
    val release_date: String,
    val track_address: String,
    val genre: String,
    val songWriter: String,
    val cover: String
) {
    fun toModel() = Song(
        id,
        title,
        lyric,
        number,
        track_length,
        Calendar.getInstance().apply {
            time.time = release_date.toLong() // FIXME: 2/4/2021 provide correct converter
        },
        track_address,
        genre,
        songWriter,
        cover, DataFactory.getSuggestedArtist()[0]
    )
}