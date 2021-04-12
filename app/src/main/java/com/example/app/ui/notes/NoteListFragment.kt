package com.example.app.ui.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.data.models.Note
import com.example.app.databinding.NoteItemBinding
import com.example.app.databinding.NoteListFragmentBinding
import com.example.app.ui.theme.AppTheme
import com.example.app.util.BaseFragment
import com.google.accompanist.glide.GlideImage
import com.google.android.material.transition.MaterialElevationScale
import java.text.DateFormat
import java.util.Date

typealias NoteListItemClickListener = (View, Note) -> Unit

class NoteListFragment : BaseFragment() {

    private lateinit var binding: NoteListFragmentBinding
    private val viewModel: NoteListViewModel by viewModels { viewModelFactory }

    companion object {
        private const val TAG = "NoteListFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoteListFragmentBinding.inflate(inflater, container, false).apply {
            composeView.setContent {
                AppTheme {
                    NotesScreen(viewModel) { note ->
                        Log.d(TAG, note.title)
                        val direction = NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(note)
                        findNavController().navigate(direction)
                    }
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setupWithNavController(findNavController())
//        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
//        NavigationUI.setupWithNavController(binding.toolbar, findNavController())

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

@Composable
fun NotesScreen(viewModel: NoteListViewModel, onItemClicked: (Note) -> Unit) {
    val notes by viewModel.notes.observeAsState()
    notes?.let { NotesContent(notes = it, onItemClicked = onItemClicked) }
}

@Composable
fun NotesContent(notes: List<Note>, onItemClicked: (Note) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home", color = MaterialTheme.colors.primary) },
                backgroundColor = Color.Transparent,
            )
        },
        content = {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(notes) { item ->
                    NoteItem(item, onItemClicked = onItemClicked)
                }
            }
        }
    )
}

@Composable
fun NoteItem(note: Note, onItemClicked: (Note) -> Unit) {
    Row(modifier = Modifier.clickable { onItemClicked(note) }) {
        Image(
            painter = painterResource(id = R.drawable.sample_feature_image),
//        GlideImage(
//            data = note.image,
//            fadeIn = true,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(16.dp)
                .height(80.dp)
                .width(80.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                maxLines = 2,
                color = MaterialTheme.colors.primary,
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    note.author,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .alignByBaseline()
                        .weight(1f),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    DateFormat.getDateInstance().format(note.createdAt),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.overline,
                    modifier = Modifier.alignByBaseline(),
                )
            }
        }
    }
}

@Preview
@Composable
fun NoteItemPreview() {
    val note = Note(
        id = 1,
        title = "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        author = "John Doe",
        createdAt = Date(),
        image = "https://picsum.photos/id/486/1280/720",
        note = ""
    )

    AppTheme {
        NoteItem(note = note, onItemClicked = {})
    }
}

@OptIn(ExperimentalStdlibApi::class)
@Preview
@Composable
fun NotesScreenPreview() {
    val notes = buildList {
        repeat(10) { i ->
            val note = Note(
                id = i,
                title = "$i sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                author = "John Doe",
                createdAt = Date(),
                image = "https://picsum.photos/id/486/1280/720",
                note = ""
            )
            add(note)
        }
    }

    AppTheme {
        NotesContent(notes = notes, onItemClicked = {})
    }
}