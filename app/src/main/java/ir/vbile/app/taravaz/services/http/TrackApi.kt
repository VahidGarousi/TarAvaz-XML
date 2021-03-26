package ir.vbile.app.taravaz.services.http

import io.reactivex.Single
import ir.vbile.app.taravaz.data.api.ArrayResponse
import ir.vbile.app.taravaz.data.api.ResTrack
import retrofit2.http.GET

interface TrackApi {
    @GET("banner/slider")
    fun getTracks(): Single<ArrayResponse<ResTrack>>

    @GET("banner/slider")
    fun getTracks(
        artistId : Int
    ): Single<ArrayResponse<ResTrack>>
}