package com.example.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    val modelJob = Job()
    val ModelScope= CoroutineScope(modelJob+ Dispatchers.Main)

    private val _Joke= MutableLiveData<JOKES>()
    val joke:LiveData<JOKES>
    get() = _Joke

    init {
        getJoke()
    }

    var _error= MutableLiveData<String>()
    val error:LiveData<String>
    get()=_error


    fun getJoke() {
        ModelScope.launch {
            val getDeferred = JokesApi.retrofitGetter.getJoke()

            try{
                _Joke.value=getDeferred.await()
                Log.i("yolo","found joke")
            }catch (error:Exception){
                _error.value=error.message
            }
        }
    }
}