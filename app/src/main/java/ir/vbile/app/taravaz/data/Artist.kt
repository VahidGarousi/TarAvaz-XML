package ir.vbile.app.taravaz.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artist(
    val id: Int,
    val name: String,
    val image: String,
    val genre : String
) : Parcelable