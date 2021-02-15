package ir.vbile.app.taravaz.feautre.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.data.Banner
import ir.vbile.app.taravaz.data.Song
import ir.vbile.app.taravaz.data.repo.BannerRepository
import ir.vbile.app.taravaz.data.repo.SongRepository
import ir.vbile.app.taravaz.extentions.asyncNetworkRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val bannerRepository: BannerRepository,
    private val songRepository: SongRepository,
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

    private val _tracks: MutableLiveData<List<Song>> = MutableLiveData()
    val tracks: LiveData<List<Song>> = _tracks


    private fun getTracks() {
        viewModelScope.launch {
            songRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { tracks ->
                    _tracks.postValue(tracks)
                }
        }
    }
}