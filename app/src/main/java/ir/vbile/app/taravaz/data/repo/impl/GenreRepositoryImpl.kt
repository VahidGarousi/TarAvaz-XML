package ir.vbile.app.taravaz.data.repo.impl

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Genre
import ir.vbile.app.taravaz.data.repo.GenreRepository
import ir.vbile.app.taravaz.data.repo.source.GenreDataSource
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val genreRemoteDataSource: GenreDataSource
) : GenreRepository {
    override fun getAll(): Single<List<Genre>> {
        return genreRemoteDataSource.getAll()
    }
}