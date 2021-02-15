package ir.vbile.app.taravaz.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Song(
    val mediaId: String,
    val title: String,
    val lyric: String,
    val number: Int,
    val trackLength: Int,
    val releaseDate: Calendar,
    val songUrl: String,
    val genre: String,
    val songWriter: String,
    val cover: String,
    val artist: Artist
) : Parcelable