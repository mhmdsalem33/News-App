package com.salem.apps.presentation.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.salem.apps.data.core.ResponseState
import com.salem.apps.databinding.ActivityMainBinding
import com.salem.apps.domain.models.ArticleModel
import com.salem.apps.presentation.extentions.hide
import com.salem.apps.presentation.extentions.show
import com.salem.apps.presentation.extentions.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch





//tools must be installed on android studio from sdk manager -> Sdk Tools .
//1-> ndk sdk
//2-> CMake
//3-> Android sdk


//1-> Mvvm architecture
//2-> Kotlin co-routines
//3-> Retrofit
//4-> Okhttp
//5-> Okhttp logging-interceptor
//6-> Dagger Hilt
//7-> Ksp

//Security part
//c++ for Storing sensitive data.
//R8 - Proguard -> Shrinking , optimization , obfuscation.
//SSL /TLS  for secure connections.


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val binding by viewBinding(ActivityMainBinding::inflate )


    private val mainMvvm : MainViewModel by viewModels()
    private val searchEdtText by lazy { binding.search.edtText() }
    private var coroutineScope : CoroutineScope ?  = null
    private val mainTag  = "testApp"
    private val newsBitCoinAdapter by lazy { NewsBitCoinAdapter() }

    override fun onCreate( savedInstanceState : Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView(binding.root)


        coroutineScope = lifecycleScope
        initCoinsRecyclerView()
        onSearchEdtTextListener()
        observeBitCoinsNewsRemoteData()





    }

    private fun initCoinsRecyclerView() {
        binding.bitcoinRecView.apply {
            adapter        = newsBitCoinAdapter
            layoutManager  = LinearLayoutManager(this@MainActivity)
        }
    }


    private fun onSearchEdtTextListener() {
        var searchJob : Job ? =  null
        searchEdtText.addTextChangedListener { text ->
            searchJob?.cancel()
            searchJob = coroutineScope?.launch {
                delay(500)
                mainMvvm.getBitCoinsNews(text.toString())
            }
        }
    }

    private fun observeBitCoinsNewsRemoteData() {
        coroutineScope?.launch {
            mainMvvm.getBitCoinsNewsResponse.collect{ response ->
                when(response){
                    is ResponseState.Loading -> {
                        Log.e(mainTag , "loading")
                        binding.progressBar.show()
                    }
                    is ResponseState.Success -> {
                        val bitCoinsArticles =  response.data?.articles ?: emptyList()
                        newsBitCoinAdapter.catchBitcoinList(bitCoinsArticles as ArrayList<ArticleModel>)
                        binding.progressBar.hide()
                        Log.e(mainTag , "success $bitCoinsArticles")
                    }
                    is ResponseState.Error -> {
                        binding.progressBar.hide()
                        Log.e(mainTag , "error ${response.message}")
                    } else -> Unit
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        coroutineScope?.cancel()
    }
    

}