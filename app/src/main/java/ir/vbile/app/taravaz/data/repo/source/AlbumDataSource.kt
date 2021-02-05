package ir.vbile.app.taravaz.data.repo.source

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Album

interface AlbumDataSource {
    fun getAll(): Single<List<Album>>
}