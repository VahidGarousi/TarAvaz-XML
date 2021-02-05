package ir.vbile.app.taravaz.services.http

import io.reactivex.Single
import ir.vbile.app.taravaz.data.api.ArrayResponse
import ir.vbile.app.taravaz.data.api.ResAlbum
import retrofit2.http.GET

interface AlbumApi {
    @GET("banner/slider")
    fun getSuggestedAlbums(): Single<ArrayResponse<ResAlbum>>
}