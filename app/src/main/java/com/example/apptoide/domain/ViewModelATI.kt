package com.example.apptoide.domain

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptoide.data.reoisitory.RepositoryModel
import com.example.apptoide.data.structure.TabsStructure
import com.example.apptoide.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelATI @Inject constructor(
    private val repository: RepositoryModel
) : ViewModel() {

    private val _itemsList: MutableStateFlow<Resource<TabsStructure>> =
        MutableStateFlow(Resource.Loading())
    val itemsList: StateFlow<Resource<TabsStructure>> = _itemsList

    val topState = LazyListState()
    val bottomBarState = LazyListState()

    init {
        getDataATI()
//        Log.i("DebugNetworkVM", "reached init")
    }

    private fun getDataATI() = viewModelScope.launch(Dispatchers.IO) {
        repository.getAllData().collectLatest { _itemsList.emit(it) }
        Log.i(
            "DebugNetworkVM",
            itemsList.value.data?.responses?.listApps?.datasets?.all?.data?.list.toString()
        )
    }
}
