package ir.vbile.app.taravaz.extentions

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): Date {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(this)
}
