package ir.vbile.app.taravaz.data.api

import ir.vbile.app.taravaz.data.Genre

data class ResGenres(
    val id: Int,
    val name: String,
    val description: String,
    val tracksCount: Int
) {
    fun toModel() = Genre(
        id, name, description, tracksCount
    )
}
