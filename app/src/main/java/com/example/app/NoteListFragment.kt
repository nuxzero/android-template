package com.example.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.data.models.Note
import com.example.app.databinding.NoteItemBinding
import com.example.app.databinding.NoteListFragmentBinding
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint

typealias NoteListItemClickListener = (View, Note) -> Unit

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private lateinit var binding: NoteListFragmentBinding
    private val viewModel: NoteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoteListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        NavigationUI.setupWithNavController(binding.toolbar, findNavController())

        postponeEnterTransition()
        binding.root.doOnPreDraw { startPostponedEnterTransition() }

        setupListView()
    }

    private fun setupListView() {
        val adapter = NoteListItemAdapter { itemView, note ->
            // Navigate to note detail
            val noteDetailTransitionName = getString(R.string.note_detail_transition_name)
            val extras = FragmentNavigatorExtras(itemView to noteDetailTransitionName)
            val direction = NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(note)
            findNavController().navigate(direction, extras)

            // Set layout motion
            exitTransition = MaterialElevationScale(false).apply {
                duration = resources.getInteger(R.integer.note_motion_duration).toLong()
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = resources.getInteger(R.integer.note_motion_duration).toLong()
            }
        }
        binding.noteList.layoutManager = LinearLayoutManager(requireContext())
        binding.noteList.adapter = adapter

        viewModel.notes.observe(viewLifecycleOwner, { notes ->
            adapter.setNotes(notes)
        })
    }
}

class NoteListItemAdapter(private val listener: NoteListItemClickListener) :
    RecyclerView.Adapter<NoteListItemViewHolder>() {

    private var _notes = listOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListItemViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteListItemViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: NoteListItemViewHolder, position: Int) {
        holder.bind(_notes[position])
    }

    override fun getItemCount(): Int = _notes.size
    fun setNotes(notes: List<Note>) {
        _notes = notes
        notifyDataSetChanged()
    }
}

class NoteListItemViewHolder(
    private val binding: NoteItemBinding,
    private val listener: NoteListItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        with(binding) {
            this.note = note
            this.root.setOnClickListener { listener.invoke(binding.root, note) }
        }
    }
}
