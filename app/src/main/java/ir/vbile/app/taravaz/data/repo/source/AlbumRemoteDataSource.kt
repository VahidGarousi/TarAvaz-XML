package ir.vbile.app.taravaz.data.repo.source

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Album
import ir.vbile.app.taravaz.data.api.ResAlbum
import ir.vbile.app.taravaz.data.api.ResTrack
import ir.vbile.app.taravaz.services.http.AlbumApi

class AlbumRemoteDataSource constructor(
    private val albumApi : AlbumApi
): AlbumDataSource {
    override fun getAll(): Single<List<Album>> {
        return albumApi.getSuggestedAlbums().map { it.data.map(ResAlbum::toModel) }
    }
}