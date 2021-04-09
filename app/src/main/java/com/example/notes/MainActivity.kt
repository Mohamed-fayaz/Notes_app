package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.RealmResults

class MainActivity : AppCompatActivity() {
    private lateinit var addNotes : FloatingActionButton
    private lateinit var notesRv : RecyclerView
    private lateinit var noteslist : ArrayList<Notes>
    private lateinit var realm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        realm = Realm.getDefaultInstance()
        addNotes = findViewById(R.id.addNotes)
        notesRv = findViewById(R.id.notesRecyclerV)


        addNotes.setOnClickListener(){
            startActivity(Intent(this,AddNotes::class.java))
            finish()
        }
        notesRv.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)

        getAllNotes()

    }

    private fun getAllNotes() {
        noteslist = ArrayList()
        val results:RealmResults<Notes> = realm.where<Notes>(Notes::class.java).findAll()
        notesRv.adapter = NotesAdapter(this,results)
        notesRv.adapter!!.notifyDataSetChanged()
    }

    //    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        if (menu != null) {
//            super.onCreateOptionsMenu(menu, inflater)
//        }
//        inflater?.inflate(R.menu.navdrawer_menu , menu)
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return NavigationUI.onNavDestinationSelected(item!!,
//            view!!.findNavController())
//                || super.onOptionsItemSelected(item)
//    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.clearmenuitem){
            realm = Realm.getDefaultInstance()
            realm.beginTransaction()

        realm.deleteAll()
            realm.commitTransaction()
        getAllNotes()
        }
        return super.onOptionsItemSelected(item)
    }
}