package com.pseedk.filmsapp.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pseedk.filmsapp.REALIZATION
import com.pseedk.filmsapp.models.MovieItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragmentViewModel: ViewModel() {

    fun insert(movieItemModel: MovieItemModel, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.insertMovie(movieItemModel) {
                onSuccess()
            }
        }

    fun delete(movieItemModel: MovieItemModel, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.deleteMovie(movieItemModel) {
                onSuccess()
            }
        }
}