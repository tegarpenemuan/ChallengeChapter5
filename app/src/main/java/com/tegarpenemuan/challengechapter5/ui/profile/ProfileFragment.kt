package com.tegarpenemuan.challengechapter5.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tegarpenemuan.challengechapter5.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        viewModel.getUser()
//        bindViewModel()

        return root
    }

//    private fun bindViewModel() {
//        viewModel.shouldShowDataUser.observe(viewLifecycleOwner) {
//           binding.etUsername.text = it.
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}