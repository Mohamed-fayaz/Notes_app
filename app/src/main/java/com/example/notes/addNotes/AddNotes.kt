package com.example.notes.addNotes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.notes.*
import com.example.notes.database.Notes
import com.example.notes.databinding.ActivityAddNotesBinding
import com.example.notes.databinding.ActivityMainBinding

import java.lang.Exception


class AddNotes : AppCompatActivity() {
    private lateinit var titleEd : EditText
    private lateinit var descriptionEd : EditText
    private lateinit var addNoteBt : Button

    private lateinit var binding: ActivityAddNotesBinding

    private val addNotesViewModel: AddNotesViewModel by viewModels {
        AddNotesViewModelFactory((application as Notesapplication).repository,(application as Notesapplication).timeRepository) }
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_notes);


        addNotesViewModel.onStartTracking()

        titleEd = binding.TitleEd
        descriptionEd = binding.DescEd
        addNoteBt = binding.addbt

        addNoteBt.setOnClickListener{
       addNotesViewModel.onStopTracking()

            addNotesToDB()
        }
    }

    private fun addNotesToDB() {
        try{
            // auto increment


            val notes = Notes()

            notes.title = titleEd.text.toString()
            notes.description = descriptionEd.text.toString()
            addNotesViewModel.insert(notes)
            Toast.makeText(this,"notes added",Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }catch (e:Exception){
            Toast.makeText(this,"error in adding notes $e",Toast.LENGTH_SHORT).show()
        }

    }
}
