package ir.vbile.app.taravaz.extentions

import android.widget.ImageView
import ir.vbile.app.taravaz.services.android.ImageLoadingProvider

fun loadImage(imageView: ImageView, url: String?) =
    ImageLoadingProvider.getService().load(imageView, url)

fun loadImage(url: String?, imageView: ImageView) =
    ImageLoadingProvider.getService().load(imageView, url)
