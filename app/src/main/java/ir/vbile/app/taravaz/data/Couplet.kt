package ir.vbile.app.taravaz.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Couplet(
    val hemistich: Pair<Hemistich, Hemistich>,
    val id: Int
) : Parcelable
