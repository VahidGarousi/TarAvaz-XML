package ir.vbile.app.taravaz.feautre.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.data.repo.ArtistRepository
import ir.vbile.app.taravaz.extentions.asyncNetworkRequest
import kotlinx.coroutines.launch

class SearchVM constructor(
    private val artistRepository: ArtistRepository
) : TarAvazViewModel() {
    private val _suggestedArtist: MutableLiveData<List<Artist>> = MutableLiveData()
    val suggestedArtist: LiveData<List<Artist>> = _suggestedArtist

    private fun getSuggestedArtist() {
        viewModelScope.launch {
            artistRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { artists ->
                    _suggestedArtist.postValue(artists)
                }
        }
    }

    init {
        getSuggestedArtist()
    }
}