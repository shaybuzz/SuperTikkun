package com.supertikkun.view.main

import androidx.lifecycle.*
import com.supertikkun.repository.TikunRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.IllegalArgumentException

class MainFragmentViewModel(private val tikunRepo: TikunRepo):ViewModel() {
    private val tikunId = "F5166F17-6D33-4210-940B-045A8392FEDD"

    val textSimple = tikunRepo.textSimple
    val textWithTaam = tikunRepo.textWithTaam

    val textChunksData = tikunRepo.textRawData

    val videoUrl = tikunRepo.videoUrl
    val audioUrl = tikunRepo.audioUrl


    val videoPosition: MutableLiveData<Int> = MutableLiveData(0)


    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // val readSection = tikunService.getReadSection2()
                // Timber.d("####view model got ## $readSection")

                tikunRepo.pullRawTextData(tikunId)
                tikunRepo.pullChunksVideoData(tikunId)
            }
            // Coroutine that will be canceled when the ViewModel is cleared.
        }
        videoPosition.observeForever {
            Timber.d("##### view model listen to tick $it")
        }
    }
}



class MainFragmentViewModelFactory(
    private val tikunRepo: TikunRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)) {
            return MainFragmentViewModel(tikunRepo) as T
        }
        throw IllegalArgumentException("cant build MainViewModelFactory ")
    }

}