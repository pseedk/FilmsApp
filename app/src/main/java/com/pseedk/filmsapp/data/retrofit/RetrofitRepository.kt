package com.pseedk.filmsapp.data.retrofit

import com.pseedk.filmsapp.data.retrofit.api.RetrofitInstance
import com.pseedk.filmsapp.models.MoviesModel
import retrofit2.Response

class RetrofitRepository {

    suspend fun getMovies(): Response<MoviesModel> {
        return RetrofitInstance.api.getPopularMovie()
    }
}