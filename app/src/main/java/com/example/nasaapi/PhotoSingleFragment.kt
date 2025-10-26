package com.example.nasaapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.nasaapi.databinding.FragmentPhotoSingleBinding

class PhotoSingleFragment : Fragment() {

    private var _binding: FragmentPhotoSingleBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoSingleBinding.inflate(layoutInflater, container, false)

        val imageUrl = arguments?.getString("PhotoURL")
        val titleAndExplanation = arguments?.getString("PhotoTITLE")

        imageUrl?.let {
            binding.photosingleImageview.load(it) {
                crossfade(true)
            }
        }

        binding.titleTextView.text = titleAndExplanation

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}