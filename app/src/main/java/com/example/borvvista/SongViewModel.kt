package com.example.borvvista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borvvista.data.SongRepository
import com.example.borvvista.domain.model.NetworkException
import com.example.borvvista.domain.model.Song
import com.example.borvvista.domain.model.getUserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val repository: SongRepository
) : ViewModel() {



    private val _uiState = MutableStateFlow<SongUiState>(SongUiState.Loading)
    val uiState: StateFlow<SongUiState> = _uiState.asStateFlow()

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        viewModelScope.launch {
            repository.getSongsFromDatabase()
                .map<List<Song>, SongUiState>(SongUiState::Success)
                .catch { e ->
                    if (e is NetworkException) {
                        emit(SongUiState.Error(e.getUserMessage()))
                    } else {
                        emit(SongUiState.Error("Unknown error occurred"))
                    }
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = SongUiState.Loading
                )
                .collect { _uiState.value = it }
        }
    }

    sealed class SongUiState {
        object Loading : SongUiState()
        data class Success(val songs: List<Song>) : SongUiState()
        data class Error(val message: String) : SongUiState()
    }
}