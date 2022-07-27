package com.pdp.declarative.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pdp.declarative.repository.TVShowRepository
import com.pdp.declarative.utils.Logger
import com.pdp.imperative.model.TVShowDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val tvShowRepository: TVShowRepository) :
    ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val tvShowDetails = MutableLiveData<TVShowDetails>()

    fun apiTVShowDetails(show_id:Int) {
        isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = tvShowRepository.apiTVShowDetails(show_id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val reps = response.body()
                    Logger.d("@@@imagePath ", reps!!.tvShow.toString())
                    tvShowDetails.postValue(reps)
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
}