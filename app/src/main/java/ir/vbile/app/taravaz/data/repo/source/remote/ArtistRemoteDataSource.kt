package ir.vbile.app.taravaz.data.repo.source.remote

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.data.api.ResArtist
import ir.vbile.app.taravaz.data.repo.source.ArtistDataSource
import ir.vbile.app.taravaz.services.http.ArtistApi
import javax.inject.Inject

class ArtistRemoteDataSource @Inject constructor(
    private val artistApi : ArtistApi
): ArtistDataSource {
    override fun getAll(): Single<List<Artist>> {
        return artistApi.getSuggestedArtists().map { it.data.map(ResArtist::toModel) }
    }
}