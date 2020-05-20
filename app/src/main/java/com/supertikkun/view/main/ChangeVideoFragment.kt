package com.supertikkun.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.supertikkun.databinding.ChangeVideoFragmentBinding

class ChangeVideoFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = ChangeVideoFragmentBinding.inflate(inflater, container, false)

        return binding.root

    }
}