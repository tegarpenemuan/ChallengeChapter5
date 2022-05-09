package com.tegarpenemuan.challengechapter5.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.tegarpenemuan.challengechapter5.Constant
import com.tegarpenemuan.challengechapter5.databinding.FragmentHomeBinding
import com.tegarpenemuan.challengechapter5.ui.home.adapter.ListGenreAdapter
import com.tegarpenemuan.challengechapter5.ui.home.adapter.MovieNowPlayingAdapter
import com.tegarpenemuan.challengechapter5.ui.home.adapter.MoviePopulerAdapter
import com.tegarpenemuan.myapplication.database.MyDatabase

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val viewModel: HomeViewModel by viewModels()
    lateinit var moviePopulerAdapter: MoviePopulerAdapter
    lateinit var movieNowPlayingAdapter: MovieNowPlayingAdapter
    lateinit var listGenreAdapter: ListGenreAdapter
    private var db: MyDatabase? = null

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
        viewModel.getUser()
        bindViewModel()

        val db = MyDatabase.getInstance(this.requireContext())
        viewModel.onViewLoaded(db)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = MyDatabase.getInstance(requireContext().applicationContext)
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

        viewModel.shouldShowUsername.observe(viewLifecycleOwner) {
            binding.tvTitle.text = it.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}