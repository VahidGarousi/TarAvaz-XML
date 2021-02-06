package ir.vbile.app.taravaz.data.repo.source

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Genre

interface GenreDataSource {
    fun getAll() : Single<List<Genre>>
}