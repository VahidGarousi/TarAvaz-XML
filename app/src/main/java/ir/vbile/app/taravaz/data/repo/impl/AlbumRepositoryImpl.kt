package ir.vbile.app.taravaz.data.repo.impl

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Album
import ir.vbile.app.taravaz.data.repo.AlbumRepository
import ir.vbile.app.taravaz.data.repo.source.AlbumDataSource
import javax.inject.Inject

class AlbumRepositoryImpl  @Inject constructor(
    private val albumRemoteDataSource: AlbumDataSource
) : AlbumRepository {
    override fun getAll(): Single<List<Album>> {
        return albumRemoteDataSource.getAll()
    }
}