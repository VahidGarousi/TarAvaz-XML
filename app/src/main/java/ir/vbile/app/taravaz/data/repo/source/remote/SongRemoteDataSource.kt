package ir.vbile.app.taravaz.data.repo.source.remote

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.data.api.ResTrack
import ir.vbile.app.taravaz.data.repo.source.SongDataSource
import ir.vbile.app.taravaz.services.http.TrackApi
import javax.inject.Inject

class SongRemoteDataSource @Inject constructor(
    private val trackApi: TrackApi
) : SongDataSource {
    override fun getAll(): Single<List<Track>> {
        return trackApi.getTracks().map { it.data.map(ResTrack::toModel) }
    }
}