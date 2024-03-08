package com.salem.apps.presentation.ui.main.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salem.apps.data.core.ResponseState
import com.salem.apps.domain.models.NewsModel
import com.salem.apps.domain.usecases.GetBitCoinNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class MainViewModel  @Inject constructor(
    private val getBitCoinNewsUseCase: GetBitCoinNewsUseCase
) : ViewModel(){


    init {
        System.loadLibrary("native-lib")
        getBitCoinsNews( query = "bitcoin" )
    }

    private external fun getEncryptedApiKey(): String


    private val _getBitCoinsNewsResponse = MutableStateFlow<ResponseState<NewsModel>>(ResponseState.Idle())
    val getBitCoinsNewsResponse = _getBitCoinsNewsResponse.asStateFlow()


    private var bitCoinsNewsJob : Job ? = null
    fun getBitCoinsNews( query : String , apiKey : String = getEncryptedApiKey() ){
        bitCoinsNewsJob?.cancel()
        bitCoinsNewsJob = viewModelScope.launch(Dispatchers.IO) {
            getBitCoinNewsUseCase( query , apiKey ).collect{
                _getBitCoinsNewsResponse.emit(it)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        bitCoinsNewsJob?.cancel()
    }


}