package ir.vbile.app.taravaz.feautre.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.taravaz.common.Constants.UPDATE_PLAYER_POSITION_INTERVAL
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.exoplayer.MusicService
import ir.vbile.app.taravaz.exoplayer.MusicServiceConnection
import ir.vbile.app.taravaz.exoplayer.currentPlaybackPosition
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerVM @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection
) : TarAvazViewModel() {
    private val playbackState = musicServiceConnection.playbackState
    private val _curSongDuration = MutableLiveData<Long>()
    val curSongDuration: LiveData<Long> = _curSongDuration

    private val _curPlayerPosition = MutableLiveData<Long>()
    val curPlayerPosition: LiveData<Long> = _curPlayerPosition

    init {
        updateCurrentPlayerPosition()
    }

    private fun updateCurrentPlayerPosition() {
        viewModelScope.launch {
            while (true) {
                val pos = playbackState.value?.currentPlaybackPosition
                if (curPlayerPosition.value != pos) {
                    _curPlayerPosition.postValue(pos)
                    _curSongDuration.postValue(MusicService.curSongDuration)
                }
                delay(UPDATE_PLAYER_POSITION_INTERVAL)
            }
        }
    }
}