package ir.vbile.app.taravaz.data.repo

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Banner

interface BannerRepository {
    fun getAll() : Single<List<Banner>>
}