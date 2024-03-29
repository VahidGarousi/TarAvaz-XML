package ir.vbile.app.taravaz.data.repo

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Track

interface SongRepository {
    fun getAll(): Single<List<Track>>
    fun getAll(artistId : Int) : Single<List<Track>>
}