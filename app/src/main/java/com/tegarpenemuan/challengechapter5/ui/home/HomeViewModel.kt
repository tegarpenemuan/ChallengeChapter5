package com.tegarpenemuan.challengechapter5.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tegarpenemuan.challengechapter5.model.movie.ListGenreModel
import com.tegarpenemuan.challengechapter5.model.movie.MoviePopulerModel
import com.tegarpenemuan.challengechapter5.network.TMDBApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    val shouldShowMoviePopuler: MutableLiveData<List<MoviePopulerModel>> = MutableLiveData()
    val shouldShowListGenre: MutableLiveData<List<ListGenreModel>> = MutableLiveData()

    fun getMoviePopuler() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = TMDBApiClient.instanceTMDB.getMoviePopuler("0fbaf8c27d542bc99bfc67fb877e3906")
            withContext(Dispatchers.IO) {
                if (response.isSuccessful) {
                    // transformasi atau mapping dari response ke model
                    val moviePopularResponse = response.body()
                    val moviePopularModels = moviePopularResponse?.results?.map {
                        MoviePopulerModel(
                            id = it.id ?: -1,
                            image = "https://image.tmdb.org/t/p/w500/" + it.poster_path.orEmpty(),
                            title = it.title.orEmpty(),
                            vote_average = it.vote_average,
                            overview = it.overview
                        )
                    } ?: listOf()
                    // yang di parsing ke livedata
                    shouldShowMoviePopuler.postValue(moviePopularModels)
                }
            }
        }
    }

    fun getListMovie() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = TMDBApiClient.instanceTMDB.getListGenre("0fbaf8c27d542bc99bfc67fb877e3906")
            withContext(Dispatchers.IO) {
                if (response.isSuccessful) {
                    // transformasi atau mapping dari response ke model
                    val listGenreResponse = response.body()
                    val listGenreModels = listGenreResponse?.genres?.map {
                        ListGenreModel(
                            id = it.id,
                            name = it.name
                        )
                    } ?: listOf()
                    // yang di parsing ke livedata
                    shouldShowListGenre.postValue(listGenreModels)
                }
            }
        }
    }
}