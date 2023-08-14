package com.davenotdavid.archcomponentsample.model


// Current state of views
interface UiState
// User actions
interface UiEvent
// Side effects like an error message that's shown only once
interface UiEffect

class MviContract {

    sealed class Event : UiEvent {
        object OnRefreshHeadlineClicked : Event()
        object OnShowToastClicked : Event()
    }

    data class State(
        val headlineState: HeadlineState
    ) : UiState

    sealed class HeadlineState {
        object Idle : HeadlineState()
        // TODO: Add error?
        object Loading : HeadlineState()
        data class Success(val headline: Headline) : HeadlineState()
    }

    sealed class Effect : UiEffect {
        object ShowToast : Effect()
    }

}
