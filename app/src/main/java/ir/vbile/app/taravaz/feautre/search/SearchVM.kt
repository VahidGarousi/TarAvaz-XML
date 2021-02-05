package ir.vbile.app.taravaz.feautre.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.data.Album
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.data.repo.AlbumRepository
import ir.vbile.app.taravaz.data.repo.ArtistRepository
import ir.vbile.app.taravaz.extentions.asyncNetworkRequest
import kotlinx.coroutines.launch

class SearchVM constructor(
    private val artistRepository: ArtistRepository,
    private val albumRepository: AlbumRepository
) : TarAvazViewModel() {
    private val _suggestedArtist: MutableLiveData<List<Artist>> = MutableLiveData()
    val suggestedArtist: LiveData<List<Artist>> = _suggestedArtist

    private val _suggestedAlbum: MutableLiveData<List<Album>> = MutableLiveData()
    val suggestedAlbum: LiveData<List<Album>> = _suggestedAlbum

    private fun getSuggestedArtist() {
        viewModelScope.launch {
            artistRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { artists ->
                    _suggestedArtist.postValue(artists)
                }
        }
    }
    private fun getSuggestedAlbums() {
        viewModelScope.launch {
            albumRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { artists ->
                    _suggestedAlbum.postValue(artists)
                }
        }
    }


    init {
        getSuggestedArtist()
        getSuggestedAlbums()
    }
}