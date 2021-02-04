package ir.vbile.app.taravaz.data.repo.demo

import io.reactivex.Single
import ir.vbile.app.taravaz.common.DataFactory
import ir.vbile.app.taravaz.data.Banner
import ir.vbile.app.taravaz.data.repo.BannerRepository
import ir.vbile.app.taravaz.data.repo.source.BannerDataSource

class DemoBannerRepositoryImpl : BannerRepository {
    override fun getAll(): Single<List<Banner>> =
        DataFactory.getAllBanners()
}