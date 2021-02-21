package ir.vbile.app.taravaz.data.repo.source

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Track

interface SongDataSource {
    fun getAll() : Single<List<Track>>
    fun getAll(artistId : Int) : Single<List<Track>>
}