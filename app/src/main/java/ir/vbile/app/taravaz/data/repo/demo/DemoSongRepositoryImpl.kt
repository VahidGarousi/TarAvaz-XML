package ir.vbile.app.taravaz.data.repo.demo

import io.reactivex.Single
import ir.vbile.app.taravaz.common.DataFactory
import ir.vbile.app.taravaz.data.Song
import ir.vbile.app.taravaz.data.repo.SongRepository

class DemoSongRepositoryImpl : SongRepository {
    override fun getAll(): Single<List<Song>> {
        return Single.just(
            DataFactory.getAllTracks()
        )
    }
}