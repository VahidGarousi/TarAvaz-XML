package ir.vbile.app.taravaz.data.repo.impl

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.data.repo.TrackRepository
import ir.vbile.app.taravaz.data.repo.source.TrackDataSource

class TrackRepositoryImpl constructor(
    private val remoteTrackDataSource: TrackDataSource
) : TrackRepository {
    override fun getAll(): Single<List<Track>> {
        return remoteTrackDataSource.getAll()
    }
}