package ir.vbile.app.taravaz.data

import java.util.*

data class Track(
    val id: Int,
    val title: String,
    val lyric: String,
    val number: Int,
    val trackLength: Int,
    val releaseDate: Calendar,
    val trackAddress: String,
    val genre: String,
    val songWriter: String,
    val cover: String
)