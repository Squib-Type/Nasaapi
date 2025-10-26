package com.example.nasaapi

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nasaapi.api.ArtworkItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"

open class PhotoGalleryViewModel : ViewModel() {

    private val photoRepository = PhotoRepository()

    private val _galleryItems: MutableStateFlow<List<ArtworkItem>> =
        MutableStateFlow(emptyList())
    open val galleryItems: Flow<List<ArtworkItem>>
        get() = _galleryItems.asStateFlow()

    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchPhotos(count = 50)
                Log.d(TAG, "Initial Items received: $items")
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
            }
        }
    }

    fun getStuff(progressBar: ProgressBar) {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchPhotos(count = 50)
                Log.d(TAG, "Refreshed Items received: $items")
                _galleryItems.value = items
                progressBar.visibility = View.GONE
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
                progressBar.visibility = View.GONE
            }
        }
    }

}

class PhotoGalleryViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>):
            T {
        return PhotoGalleryViewModel() as T
    }
}