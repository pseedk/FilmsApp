package com.pseedk.filmsapp.data.retrofit.api

import com.pseedk.filmsapp.models.MovieItemModel
import com.pseedk.filmsapp.models.MoviesModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("3/movie/popular?api_key=9fd0d9d1ed8fe5a72f755ef1275d7e67&language=en-US&page=1")
    suspend fun getPopularMovie(): Response<MoviesModel>
}