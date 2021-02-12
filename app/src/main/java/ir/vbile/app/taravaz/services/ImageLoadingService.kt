package ir.vbile.app.taravaz.services

import android.widget.ImageView


interface ImageLoadingService {
    fun load(imageView: ImageView, imageUrl: String?)
}