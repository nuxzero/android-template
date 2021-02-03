package com.example.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.data.models.Note
import com.example.app.databinding.NoteItemBinding
import com.example.app.databinding.NoteListFragmentBinding

typealias NoteListItemClickListener = (Note) -> Unit

class NoteListFragment : Fragment() {

    private lateinit var binding: NoteListFragmentBinding
    private lateinit var viewModel: NoteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoteListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NoteListViewModel::class.java)

        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)

        setupListView()
    }

    private fun setupListView() {
        val adapter = NoteListItemAdapter { note -> TODO() }
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
    }
}

class NoteListItemViewHolder(
    private val binding: NoteItemBinding,
    private val listener: NoteListItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        with(binding) {
            titleText.text = note.title
            root.setOnClickListener { listener.invoke(note) }
        }
    }
}
