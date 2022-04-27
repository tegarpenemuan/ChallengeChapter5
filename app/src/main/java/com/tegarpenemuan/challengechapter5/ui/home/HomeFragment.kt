package com.tegarpenemuan.challengechapter5.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tegarpenemuan.challengechapter5.databinding.FragmentHomeBinding
import com.tegarpenemuan.challengechapter5.ui.home.adapter.ListGenreAdapter
import com.tegarpenemuan.challengechapter5.ui.home.adapter.MovieNowPlayingAdapter
import com.tegarpenemuan.challengechapter5.ui.home.adapter.MoviePopulerAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val viewModel: HomeViewModel by viewModels()
    lateinit var moviePopulerAdapter: MoviePopulerAdapter
    lateinit var movieNowPlayingAdapter: MovieNowPlayingAdapter
    lateinit var listGenreAdapter: ListGenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        moviePopulerAdapter = MoviePopulerAdapter(emptyList())
        binding.rvMoviePopuler.adapter = moviePopulerAdapter

        movieNowPlayingAdapter = MovieNowPlayingAdapter(emptyList())
        binding.rvMovieNowPlaying.adapter = movieNowPlayingAdapter

        listGenreAdapter = ListGenreAdapter(emptyList())
        binding.rvListGenre.adapter = listGenreAdapter

        viewModel.getMoviePopuler()
        viewModel.getListMovie()
        bindViewModel()

        return root
    }

    private fun bindViewModel() {
        viewModel.shouldShowMoviePopuler.observe(requireActivity()) {
            moviePopulerAdapter.updateList(it)
        }

        viewModel.shouldShowMoviePopuler.observe(requireActivity()) {
            movieNowPlayingAdapter.updateList(it)
        }

        viewModel.shouldShowListGenre.observe(requireActivity()) {
           listGenreAdapter.updateList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}