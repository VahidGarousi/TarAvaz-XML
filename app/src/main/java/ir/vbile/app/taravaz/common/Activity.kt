package ir.vbile.app.taravaz.common

import android.content.Context
import android.content.Intent
import android.os.Bundle

fun <T> Context.openActivity(target: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, target)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}