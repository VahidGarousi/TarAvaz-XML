package ir.vbile.app.taravaz.feautre.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.data.Banner
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.data.repo.BannerRepository
import ir.vbile.app.taravaz.data.repo.TrackRepository
import ir.vbile.app.taravaz.extentions.asyncNetworkRequest
import kotlinx.coroutines.launch

class HomeVM constructor(
    private val bannerRepository: BannerRepository,
    private val trackRepository: TrackRepository,
) : TarAvazViewModel() {
    private val _banners: MutableLiveData<List<Banner>> = MutableLiveData()
    val banners: LiveData<List<Banner>> = _banners

    init {
        getBanners()
        getTracks()
    }

    private fun getBanners() {
        viewModelScope.launch {
            bannerRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { banners ->
                    _banners.postValue(banners)
                }
        }
    }

    private val _tracks: MutableLiveData<List<Track>> = MutableLiveData()
    val tracks: LiveData<List<Track>> = _tracks


    private fun getTracks() {
        viewModelScope.launch {
            trackRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { tracks ->
                    _tracks.postValue(tracks)
                }
        }
    }
}