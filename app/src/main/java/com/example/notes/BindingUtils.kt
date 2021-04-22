package com.example.notes

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.notes.database.Notes
import com.example.notes.timer.TimeUsed
import java.util.*

@BindingAdapter("setTitileText")
fun TextView.setTitleText(item : Notes){
    item.let {
        text = it.title
    }

}

@BindingAdapter("setDescriptionText")
fun TextView.setDescriptionText(item : Notes){
    item.let{
        text = it.description
    }
}
@BindingAdapter("StartTime")
fun TextView.StartTime(item : TimeUsed){
    item.let {
        text = it.startTimeMilli.formatTime()
    }
}

@BindingAdapter("EndTime")
fun TextView.EndTime(item : TimeUsed){
    item.let {
        text = it.endTimeMilli.formatTime()
    }
}
fun Long.formatTime():String{
    val cl: Calendar = Calendar.getInstance()
    cl.setTimeInMillis(this) //here your time in miliseconds

    val date = "" + cl.get(Calendar.DAY_OF_MONTH).toString() + ":" + cl.get(Calendar.MONTH)
        .toString() + ":" + cl.get(Calendar.YEAR)
    val time = "" + cl.get(Calendar.HOUR_OF_DAY).toString() + ":" + cl.get(Calendar.MINUTE).toString() + ":" + cl.get(
        Calendar.SECOND)
    return "$date  $time"
}
@BindingAdapter("userId")
fun TextView.Userid(item : TimeUsed){
    item.let {
        text = it.usedId.toString()
    }
}

@BindingAdapter("TimeTaken")
fun TextView.TimeTaken(item : TimeUsed){
    item.let {
        text = timeTakenCalculator(it)
    }
}

private fun timeTakenCalculator(time: TimeUsed):String{
    val timetakenMilli  = time.startTimeMilli - time.endTimeMilli
    val String1 = "" + Math.abs(((timetakenMilli / 1000) / 60)) +  " : " + Math.abs(((timetakenMilli / 1000) % 60))
    return String1
}