package com.supertikkun.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.supertikkun.network.TikunService
import com.supertikkun.network.response.ReadSectionsResponse
import com.supertikkun.repository.TikunRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class MainViewModel(private val tikunService: TikunService, private val tikunRepo: TikunRepo) :
    ViewModel() {

    //val readSection: LiveData<ReadSectionsResponse> = tikunService.getTextChunks()
    //val textTikunData = tikunRepo.textRawData

    init {

    }


}

class MainViewModelFactory(
    private val tikunService: TikunService,
    private val tikunRepo: TikunRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(tikunService, tikunRepo) as T
        }
        throw IllegalArgumentException("cant build MainViewModelFactory ")
    }

}