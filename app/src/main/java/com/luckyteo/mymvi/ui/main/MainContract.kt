package com.luckyteo.mymvi.ui.main

import com.luckyteo.mymvi.ui.base.ViewEffect
import com.luckyteo.mymvi.ui.base.ViewEvent
import com.luckyteo.mymvi.ui.base.ViewState

class MainContract {

    sealed class Event : ViewEvent {
        object OnRandomNumberClicked : Event()
        object OnShowToastClicked : Event()
    }

    data class State(
        val randomNumberState: RandomNumberState
    ) : ViewState

    sealed class RandomNumberState {
        object Idle : RandomNumberState()
        object Loading : RandomNumberState()
        data class Success(val number : Int) : RandomNumberState()
    }

    sealed class Effect : ViewEffect {

        object ShowToast : Effect()

    }

}