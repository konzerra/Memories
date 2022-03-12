package com.konzerra.memories.presentation.help

import android.content.res.Resources
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    private val _tips = mutableStateOf<List<String>>(emptyList())
    val tips : State<List<String>> = _tips

    private val _searchedTips = mutableStateOf<List<String>>(emptyList())
    val searchedTips : State<List<String>> = _searchedTips

    fun searchByKey(){
        _searchedTips.value = _tips.value.filter {
            it.contains(_searchText.value, ignoreCase = true)
        }
    }
    fun setSearchText(searchText:String){
        _searchText.value = searchText
    }
    fun initTips(list:List<String>){
        _tips.value = list
    }

}