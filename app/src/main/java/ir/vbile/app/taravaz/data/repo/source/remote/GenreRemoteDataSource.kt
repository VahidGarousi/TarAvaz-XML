package ir.vbile.app.taravaz.data.repo.source.remote

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Genre
import ir.vbile.app.taravaz.data.repo.source.GenreDataSource
import ir.vbile.app.taravaz.services.http.GenreApi
import javax.inject.Inject

class GenreRemoteDataSource @Inject constructor(
    private val genreApi : GenreApi
) : GenreDataSource {
    override fun getAll(): Single<List<Genre>> {
        return genreApi.getAll()
    }
}