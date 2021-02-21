package ir.vbile.app.taravaz.data.repo.impl

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.data.repo.SongRepository
import ir.vbile.app.taravaz.data.repo.source.SongDataSource
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val remoteSongDataSource: SongDataSource
) : SongRepository {
    override fun getAll(): Single<List<Track>> {
        return remoteSongDataSource.getAll()
    }

    override fun getAll(artistId: Int): Single<List<Track>> {
        return remoteSongDataSource.getAll(artistId)
    }
}