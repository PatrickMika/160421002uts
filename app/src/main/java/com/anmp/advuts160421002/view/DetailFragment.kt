package com.anmp.advuts160421002.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anmp.advuts160421002.R
import com.anmp.advuts160421002.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(
            inflater,
            container, false
        )
        return binding.root

    }
}