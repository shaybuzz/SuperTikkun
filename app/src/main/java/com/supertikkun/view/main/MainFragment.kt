package com.supertikkun.view.main

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.supertikkun.databinding.MainFragmentBinding
import com.supertikkun.local.TikunDataBase
import com.supertikkun.network.NetworkService
import com.supertikkun.repository.TikunRepoImpl
import kotlinx.android.synthetic.main.main_fragment.*
import timber.log.Timber


class MainFragment:Fragment() {

    lateinit var mainFragmentViewModel: MainFragmentViewModel
    private lateinit var ctlr: MediaController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        val tikunService = NetworkService.tikunService
        val tikunRepo = TikunRepoImpl(TikunDataBase.getDatabase(requireContext()), tikunService)
        ctlr = MediaController(requireContext());

        binding.nextBtn.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToChangeVideoFragment())
        }

        var skip = true

        mainFragmentViewModel = ViewModelProvider(this, MainFragmentViewModelFactory(tikunRepo)).get(MainFragmentViewModel::class.java)
        binding.viewModel = mainFragmentViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        mainFragmentViewModel.videoUrl.observe(viewLifecycleOwner, Observer {
            Timber.d("### video goturi ${it}")
            ctlr.setMediaPlayer(binding.videoView)
            ctlr.setAnchorView(binding.videoView)
            binding.videoView.setMediaController(ctlr);
            it?.let {
                binding.videoView.setVideoURI(it)
            }
        })

//        binding.videoView.currentPositionVal.observe(viewLifecycleOwner, Observer {
//            Timber.d("#### current video progress $it")
//        })


        return binding.root

    }
}