package ir.vbile.app.taravaz.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
@Parcelize
data class Album(
    val id: Int,
    val name: String,
    val release_date: Calendar,
    val description: String,
    val cover: String,
    val artist: Artist
) : Parcelable
