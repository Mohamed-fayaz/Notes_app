    package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.addNotes.AddNotes
import com.example.notes.database.Notes
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.timer.timerShow
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var addNotes : FloatingActionButton
    private lateinit var notesRv : RecyclerView
    private lateinit var binding: ActivityMainBinding


    private val NotesViewModel: NotesViewModel by viewModels {
        NotesViewModelFactory(
            (application as Notesapplication).repository,
            (application as Notesapplication).timeRepository
        )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        addNotes = binding.addNotes
        notesRv = binding.notesRecyclerV


        addNotes.setOnClickListener() {

            startActivity(Intent(this, AddNotes::class.java))
            finish()
        }
        notesRv.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        val adapter = NotesAdapter()
        notesRv.adapter = adapter


        NotesViewModel.allWords.observe(this, { note: List<Notes> ->
            adapter.submitList(note)
        })


    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater: MenuInflater = menuInflater
            inflater.inflate(R.menu.main_menu, menu)

            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId == R.id.clearmenuitem) {
                Toast.makeText(this, "feature not available yet ", Toast.LENGTH_SHORT).show()
                NotesViewModel.deleteall()

            }
            if (item.itemId == R.id.showtiming) {
                val i = Intent(applicationContext, timerShow::class.java)
                startActivity(i)
            }
            return super.onOptionsItemSelected(item)
        }

}