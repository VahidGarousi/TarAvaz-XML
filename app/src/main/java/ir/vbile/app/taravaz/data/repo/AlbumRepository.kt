package ir.vbile.app.taravaz.data.repo

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Album

interface AlbumRepository {
    fun getAll(): Single<List<Album>>
}