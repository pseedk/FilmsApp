package com.pseedk.filmsapp.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pseedk.filmsapp.REALIZATION
import com.pseedk.filmsapp.data.room.repository.MoviesRepositoryRealization
import com.pseedk.filmsapp.models.MovieItemModel

class FavoriteFragmentViewModel : ViewModel() {

    fun getAllMovies(): LiveData<List<MovieItemModel>> {
        return REALIZATION.allMovies
    }
}