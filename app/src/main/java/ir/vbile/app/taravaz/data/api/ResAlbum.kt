package ir.vbile.app.taravaz.data.api

import ir.vbile.app.taravaz.data.Album
import ir.vbile.app.taravaz.extentions.toDate
import java.util.*

data class ResAlbum(
    val id: Int,
    val name: String,
    val release_date: String,
    val description: String,
    val cover: String,
    val artist: ResArtist
) {
    fun toModel() = Album(
        id,
        name,
        Calendar.getInstance().apply {
            time = release_date.toDate()
        },
        description,
        cover,
        artist = artist.toModel()
    )
}