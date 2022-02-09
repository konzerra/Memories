package com.konzerra.memories.presentation.help

import android.content.res.Resources
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringArrayResource
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import com.konzerra.memories.R
import com.konzerra.memories.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HelpScreenViewModel @Inject constructor() : ViewModel()
{
    private val _searchText = mutableStateOf("")
    val searchText : State<String> = _searchText

    fun searchByKey(){

    }
    fun setSearchText(searchText:String){
        _searchText.value = searchText
    }

}