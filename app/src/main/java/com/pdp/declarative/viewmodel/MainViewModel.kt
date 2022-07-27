package com.pdp.declarative.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdp.declarative.repository.TVShowRepository
import com.pdp.declarative.utils.Logger
import com.pdp.imperative.model.TVShow
import com.pdp.imperative.model.TVShowPopular
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val tvShowRepository: TVShowRepository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val tvShowsFromApi = MutableLiveData<ArrayList<TVShow>>()
    val tvShowsFromDB = MutableLiveData<List<TVShow>>()

    val tvShowPopular = MutableLiveData<TVShowPopular>()

    init {
        apiTVShowPopular(1)
    }

    /**
     * Retrofit Related
     */

    fun apiTVShowPopular(page: Int) {
        isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = tvShowRepository.apiTVShowPopular(page)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val resp = response.body()
                    Logger.d("MainViewModel",resp.toString())
                    tvShowPopular.postValue(resp)
                    tvShowsFromApi.postValue(resp!!.tv_shows)
                    isLoading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        isLoading.value = false
    }

    /**
     * Room Related
     */
    fun getTVShowsFromDB() {
        viewModelScope.launch {
            val tvShows = tvShowRepository.getTVShowsFromDB()
            tvShowsFromDB.postValue(tvShows)
        }
    }

    fun insertTVShowToDB(tvShow: TVShow) {
        viewModelScope.launch {
            tvShowRepository.insertTVShowToDB(tvShow)
        }
    }

    fun deleteTvShowsFromDB() {
        viewModelScope.launch {
            tvShowRepository.deleteTvShowsFromDB()
        }
    }
}