package ir.vbile.app.taravaz.data.repo.impl

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.data.repo.ArtistRepository
import ir.vbile.app.taravaz.data.repo.source.ArtistDataSource
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val remoteArtistDataSource: ArtistDataSource
) : ArtistRepository {
    override fun getAll(): Single<List<Artist>> {
        return remoteArtistDataSource.getAll()
    }
}