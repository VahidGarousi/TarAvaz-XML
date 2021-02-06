package ir.vbile.app.taravaz.feautre.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.data.repo.ArtistRepository
import ir.vbile.app.taravaz.extentions.asyncNetworkRequest
import kotlinx.coroutines.launch

class ArtistVM constructor(
    private val artistRepository: ArtistRepository
) : TarAvazViewModel() {
    private val _artists: MutableLiveData<List<Artist>> = MutableLiveData()
    val artists: LiveData<List<Artist>> = _artists

    init {
        getArtists()
    }

    private fun getArtists() {
        viewModelScope.launch {
            artistRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { artists ->
                    _artists.postValue(artists)
                }
        }
    }
}