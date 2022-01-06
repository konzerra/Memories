package com.konzerra.memories

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.konzerra.memories.domain.model.Memory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel(){
    private val _currentScreen = mutableStateOf<String>("home_screen")
    val currentScreen : State<String> = _currentScreen
    private val _currentMemory = mutableStateOf<Memory>(Memory("", emptyList(),"", ""))
    val currentMemory : State<Memory> = _currentMemory
    private val _navControllerState = mutableStateOf<Bundle>(Bundle.EMPTY)
    val navControllerState : State<Bundle> = _navControllerState

    fun setCurrentMemory(memory: Memory){

        _currentMemory.value = memory
        Log.w("testingMemory",memory.text)
    }
    fun saveNavControllerState(state: Bundle){
        _navControllerState.value = state
    }
    fun setCurrentScreen(screenRoute:String){
        _currentScreen.value = screenRoute
    }
}