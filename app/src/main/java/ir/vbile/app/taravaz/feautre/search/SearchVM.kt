package ir.vbile.app.taravaz.feautre.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.data.Album
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.data.Song
import ir.vbile.app.taravaz.data.repo.AlbumRepository
import ir.vbile.app.taravaz.data.repo.ArtistRepository
import ir.vbile.app.taravaz.data.repo.SongRepository
import ir.vbile.app.taravaz.extentions.asyncNetworkRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchVM @Inject constructor(
    private val artistRepository: ArtistRepository,
    private val albumRepository: AlbumRepository,
    private val songRepository: SongRepository
) : TarAvazViewModel() {
    var sort: Int = 0
    val selectedSortTitle: MutableLiveData<Int> = MutableLiveData()
    val sortTitles = arrayOf(
        R.string.sortLatest,
        R.string.sortPopular,
        R.string.sortPriceHighToLow,
        R.string.sortPriceLowToHigh
    )



    private val _suggestedArtist: MutableLiveData<List<Artist>> = MutableLiveData()
    val suggestedArtist: LiveData<List<Artist>> = _suggestedArtist

    private val _suggestedAlbum: MutableLiveData<List<Album>> = MutableLiveData()
    val suggestedAlbum: LiveData<List<Album>> = _suggestedAlbum


    private val _suggestedTracks: MutableLiveData<List<Song>> = MutableLiveData()
    val suggestedTracks: LiveData<List<Song>> = _suggestedTracks



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

    private fun getSuggestedTracks() {
        viewModelScope.launch {
            songRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { tracks ->
                    val list = tracks.toMutableList()
                    list.addAll(tracks)
                    list.addAll(tracks)
                    list.addAll(tracks)
                    _suggestedTracks.postValue(list)
                }
        }
    }


    init {
        getSuggestedArtist()
        getSuggestedAlbums()
        getSuggestedTracks()
        selectedSortTitle.value = sort
    }

    fun onSelectedSortChangeByUseR(sort: Int) {
        this.sort = sort
        selectedSortTitle.value = sortTitles[sort]
    }
}