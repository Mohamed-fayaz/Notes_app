package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import java.lang.Exception


class AddNotes : AppCompatActivity() {
    private lateinit var titleEd : EditText
    private lateinit var descriptionEd : EditText
    private lateinit var addNoteBt : Button
    private lateinit var realm : Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        realm = Realm.getDefaultInstance()
        titleEd = findViewById(R.id.TitleEd)
        descriptionEd = findViewById(R.id.DescEd)
        addNoteBt = findViewById(R.id.addbt)

        addNoteBt.setOnClickListener{
            addNotesToDB()
        }
    }

    private fun addNotesToDB() {
        try{
            // auto increment
            realm.beginTransaction()
            val currentIdNumber : Number? = realm.where(Notes::class.java).max("id")
            val nextID :Int
            nextID = if(currentIdNumber == null){
                1
            }else{
                currentIdNumber.toInt()+1
            }

            val notes = Notes()

            notes.title = titleEd.text.toString()
            notes.description = descriptionEd.text.toString()
            notes.id = nextID


            realm.copyToRealmOrUpdate(notes)
            realm.commitTransaction()
            Toast.makeText(this,"notes added",Toast.LENGTH_SHORT).show()

            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }catch (e:Exception){
            Toast.makeText(this,"error in adding notes $e",Toast.LENGTH_SHORT).show()
        }

    }
}