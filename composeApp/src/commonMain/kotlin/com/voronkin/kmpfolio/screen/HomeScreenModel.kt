package com.voronkin.kmpfolio.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.voronkin.kmpfolio.data.Resume
import com.voronkin.kmpfolio.data.ResumeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenModel : ScreenModel {

    private val repository = ResumeRepository()

    private val _state = MutableStateFlow<ResumeState>(ResumeState.Loading)
    val state: StateFlow<ResumeState> = _state

    init {
        loadResume()
    }

    private fun loadResume() {
        screenModelScope.launch {
            _state.value = ResumeState.Loading
            try {
                val resume = repository.loadResume()
                _state.value = ResumeState.Success(resume)
            } catch (e: Exception) {
                _state.value = ResumeState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class ResumeState {
    data object Loading : ResumeState()
    data class Success(val resume: Resume) : ResumeState()
    data class Error(val message: String) : ResumeState()
}
