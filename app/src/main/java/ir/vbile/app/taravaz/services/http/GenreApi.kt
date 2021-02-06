package ir.vbile.app.taravaz.services.http

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Genre

interface GenreApi {
    fun getAll(): Single<List<Genre>>
}