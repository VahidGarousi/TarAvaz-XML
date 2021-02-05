package ir.vbile.app.taravaz.data.repo.source

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.data.api.ResTrack
import ir.vbile.app.taravaz.services.http.TrackApi

class TrackRemoteDataSource constructor(
    private val trackApi: TrackApi
) : TrackDataSource {
    override fun getAll(): Single<List<Track>> {
        return trackApi.getTracks().map { it.data.map(ResTrack::toModel) }
    }
}