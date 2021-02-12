package ir.vbile.app.taravaz.data.repo.demo

import io.reactivex.Single
import ir.vbile.app.taravaz.common.DataFactory
import ir.vbile.app.taravaz.data.Album
import ir.vbile.app.taravaz.data.repo.AlbumRepository

class DemoAlbumRepositoryImpl  : AlbumRepository {
    override fun getAll(): Single<List<Album>> {
        return Single.just(DataFactory.getSuggestedAlbums())
    }
}