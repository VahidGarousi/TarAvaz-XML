package ir.vbile.app.taravaz.data.repo.demo

import io.reactivex.Single
import ir.vbile.app.taravaz.common.DataFactory
import ir.vbile.app.taravaz.data.Genre
import ir.vbile.app.taravaz.data.repo.GenreRepository

class DemoGenreRepository : GenreRepository{
    override fun getAll(): Single<List<Genre>> {
        return Single.just(DataFactory.getAllGenres())
    }

}