package ir.vbile.app.taravaz.data.repo.source

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Banner

interface BannerDataSource {
    fun getAll(): Single<List<Banner>>
}