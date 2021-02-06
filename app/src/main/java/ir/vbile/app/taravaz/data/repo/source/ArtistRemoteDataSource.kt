package ir.vbile.app.taravaz.data.repo.source

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.data.api.ResArtist
import ir.vbile.app.taravaz.data.api.ResTrack
import ir.vbile.app.taravaz.services.http.ArtistApi

class ArtistRemoteDataSource constructor(
    private val artistApi : ArtistApi
): ArtistDataSource {
    override fun getAll(): Single<List<Artist>> {
        return artistApi.getSuggestedArtists().map { it.data.map(ResArtist::toModel) }
    }
}