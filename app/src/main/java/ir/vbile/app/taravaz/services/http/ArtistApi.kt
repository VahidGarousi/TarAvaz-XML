package ir.vbile.app.taravaz.services.http

import io.reactivex.Single
import ir.vbile.app.taravaz.data.api.ArrayResponse
import ir.vbile.app.taravaz.data.api.ResArtist
import ir.vbile.app.taravaz.data.api.ResTrack
import retrofit2.http.GET

interface ArtistApi {
    @GET("banner/slider")
    fun getSuggestedArtists(): Single<ArrayResponse<ResArtist>>
}