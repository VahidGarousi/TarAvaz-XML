package ir.vbile.app.taravaz.data

import java.util.*

data class Album(
    val id: Int,
    val name: String,
    val release_date: Calendar,
    val description: String,
    val cover: String
)
