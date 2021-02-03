package com.example.app

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.app.databinding.NoteDetailFragmentBinding

class NoteDetailFragment : Fragment() {

    private lateinit var binding: NoteDetailFragmentBinding

    private lateinit var viewModel: NoteDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = NoteDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NoteDetailViewModel::class.java)

        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        NavigationUI.setupWithNavController(binding.toolbar, findNavController())

        val args = navArgs<NoteDetailFragmentArgs>().value
        binding.note = args.note
    }
}
