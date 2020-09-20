package com.ATG.task1.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ATG.task1.Networking.WebClient
import com.ATG.task1.domain.photo
import kotlinx.coroutines.launch

class photosViewModel : ViewModel(){

        private val mutablePhotosListLiveData = MutableLiveData<List<photo>>()
        private val photosListLiveData: LiveData<List<photo>> = mutablePhotosListLiveData

        var photosAdapter = PhotosAdapter()

        fun loadPhotos(): LiveData<List<photo>> {
            viewModelScope.launch {
                val searchResponse = WebClient.client.fetchImages()
                val photosList = searchResponse.photos.photo.map { photo ->
                    photo(
                        id = photo.id,
                        url = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg",
                        title = photo.title
                    )
                }
                mutablePhotosListLiveData.postValue(photosList)
            }
            return photosListLiveData
        }
    }