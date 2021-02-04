package ir.vbile.app.taravaz.services

import ir.vbile.app.taravaz.view.TarAvazImageView


interface ImageLoadingService {
    fun load(imageView: TarAvazImageView, imageUrl: String)
}