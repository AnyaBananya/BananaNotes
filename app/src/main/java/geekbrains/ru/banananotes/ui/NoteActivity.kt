package geekbrains.ru.banananotes.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import geekbrains.ru.banananotes.R
import geekbrains.ru.banananotes.databinding.ActivityNoteBinding
import geekbrains.ru.banananotes.model.Color
import geekbrains.ru.banananotes.model.Note
import geekbrains.ru.banananotes.viewmodel.MainViewModel
import geekbrains.ru.banananotes.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

private const val SAVE_DELAY = 2000L

class NoteActivity : BaseActivity<Note?, NoteViewState>() {
    companion object {
        const val EXTRA_NOTE = "NoteActivity.extra.NOTE"

        fun getStartIntent(context: Context, noteId: String?): Intent {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, noteId)
            return intent
        }
    }

    private var note: Note? = null
    private lateinit var ui: ActivityNoteBinding
    //by lazy { ActivityNoteBinding.inflate(layoutInflater) }
    override val layoutRes: Int = R.layout.activity_note
    override val viewModel: NoteViewModel by lazy { ViewModelProvider(this).get(NoteViewModel::class.java) }
    private val textChangeListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            triggerSaveNote()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //do nothing
        }

        override fun afterTextChanged(s: Editable?) {
            //do nothing
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(ui.root)

        val noteId = intent.getStringExtra(EXTRA_NOTE)
        if (noteId != null) {
        }
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        noteId?.let {
            viewModel.loadNote(it)
        }

        if (noteId == null) supportActionBar?.title = getString(R.string.new_note_title)

        initView()
    }

    private fun initView() {
        ui.titleEt.setText(note?.title ?: "")
        ui.bodyEt.setText(note?.note ?: "")

        val color = when (note?.color) {
            Color.WHITE -> R.color.color_white
            Color.VIOLET -> R.color.color_violet
            Color.YELLOW -> R.color.color_yellow
            Color.RED -> R.color.color_red
            Color.PINK -> R.color.color_pink
            Color.GREEN -> R.color.color_green
            Color.BLUE -> R.color.color_blue
            else -> R.color.color_yellow
        }

        ui.toolbar.setBackgroundColor(resources.getColor(color))
        ui.titleEt.addTextChangedListener(textChangeListener)
        ui.bodyEt.addTextChangedListener(textChangeListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun createNewNote(): Note {
        return viewModel.addNote(
            ui.titleEt.text.toString(),
            ui.bodyEt.text.toString()
        )
    }

    private fun triggerSaveNote() {
        if (ui.titleEt.text == null || ui.titleEt.text!!.length < 3) return

        Handler(Looper.getMainLooper()).postDelayed({
            note = note?.copy(
                title = ui.titleEt.text.toString(),
                note = ui.bodyEt.text.toString(),
                lastChanged = Date()
            )?: createNewNote()

            if (note != null) viewModel.saveChanges(note!!)
        }, SAVE_DELAY)
    }

    override fun renderData(data: Note?) {
        this.note = data
        initView()
    }
}