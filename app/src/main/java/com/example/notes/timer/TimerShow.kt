package com.example.notes.timer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.Notesapplication
import com.example.notes.R
import com.example.notes.databinding.ActivityTimerShowBinding

class timerShow : AppCompatActivity() {
    private lateinit var binding : ActivityTimerShowBinding
    private val timerShowViewModel: TimerShowViewModel by viewModels {
        TimerShowViewModelFactory((application as Notesapplication).timeRepository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_timer_show);
        var timeRv= binding.timerv
        timeRv.layoutManager = LinearLayoutManager(this)
        val adapter = TimeAdapter()
        timeRv.adapter = adapter


        timerShowViewModel.allTime.observe(this, { time->
       adapter.submitList(time)


    })




    }}

