package ir.vbile.app.taravaz.data.api

import ir.vbile.app.taravaz.data.Artist

data class ResArtist(
    val id: Int,
    val name: String,
    val image: String,
    val genre : String
) {
    fun toModel() = Artist(
        id, name, image, genre
    )
}
