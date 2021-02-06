package ir.vbile.app.taravaz.data.repo.source

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Genre
import ir.vbile.app.taravaz.services.http.GenreApi

class GenreRemoteDataSource constructor(
    private val genreApi : GenreApi
) : GenreDataSource {
    override fun getAll(): Single<List<Genre>> {
        return genreApi.getAll()
    }
}