package com.pseedk.filmsapp.screens.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pseedk.filmsapp.REALIZATION
import com.pseedk.filmsapp.data.retrofit.RetrofitRepository
import com.pseedk.filmsapp.data.room.MoviesRoomDatabase
import com.pseedk.filmsapp.data.room.repository.MoviesRepositoryRealization
import com.pseedk.filmsapp.models.MoviesModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RetrofitRepository()
    val myMovies: MutableLiveData<Response<MoviesModel>> = MutableLiveData()
    val context = application

    fun getMoviesRetrofit() {
        viewModelScope.launch {

            try {
                myMovies.value = repository.getMovies()
            } catch (e: Exception) {
                Log.e("ERROR", e.message.toString())
            }

        }
    }

    fun initDatabase() {
        val daoMovie = MoviesRoomDatabase.getInstance(context).getMovieDao()
        REALIZATION = MoviesRepositoryRealization(daoMovie)
    }
}