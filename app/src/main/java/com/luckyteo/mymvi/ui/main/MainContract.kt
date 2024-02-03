package com.luckyteo.mymvi.ui.main

import com.luckyteo.mymvi.ui.base.ViewEffect
import com.luckyteo.mymvi.ui.base.ViewEvent
import com.luckyteo.mymvi.ui.base.ViewState

class MainContract {

    sealed class Event : ViewEvent {
        data object OnRandomNumberClicked : Event()
        data object OnShowToastClicked : Event()
    }

    data class State(
        val randomNumberState: RandomNumberState
    ) : ViewState

    sealed class RandomNumberState {
        data object Idle : RandomNumberState()
        data object Loading : RandomNumberState()
        data class Success(val number : Int) : RandomNumberState()
    }

    sealed class Effect : ViewEffect {

        data object ShowToast : Effect()

    }

}