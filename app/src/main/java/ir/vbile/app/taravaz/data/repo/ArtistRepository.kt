package ir.vbile.app.taravaz.data.repo

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Artist

interface ArtistRepository {
    fun getAll(): Single<List<Artist>>
}