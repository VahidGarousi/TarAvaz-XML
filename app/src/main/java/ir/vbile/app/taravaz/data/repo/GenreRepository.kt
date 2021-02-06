package ir.vbile.app.taravaz.data.repo

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Genre

interface GenreRepository {
    fun getAll() : Single<List<Genre>>
}