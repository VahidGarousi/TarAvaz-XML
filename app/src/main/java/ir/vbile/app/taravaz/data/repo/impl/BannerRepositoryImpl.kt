package ir.vbile.app.taravaz.data.repo.impl

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Banner
import ir.vbile.app.taravaz.data.repo.BannerRepository
import ir.vbile.app.taravaz.data.repo.source.BannerDataSource

class BannerRepositoryImpl constructor(
    private val remoteBannerDataSource: BannerDataSource
) : BannerRepository {
    override fun getAll(): Single<List<Banner>> =
        remoteBannerDataSource.getAll()
}