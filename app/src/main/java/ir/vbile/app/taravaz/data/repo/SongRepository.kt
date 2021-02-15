package ir.vbile.app.taravaz.data.repo

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Song

interface SongRepository {
    fun getAll(): Single<List<Song>>
}