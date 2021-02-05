package ir.vbile.app.taravaz.data.repo.source

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Artist

interface ArtistDataSource {
    fun getAll(): Single<List<Artist>>
}