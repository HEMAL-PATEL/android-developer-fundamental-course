package com.example.gooner10.androiddeveloperfundamentals.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.gooner10.androiddeveloperfundamentals.R
import java.util.*

class AlarmActivity : AppCompatActivity() {
    private val ALARM_REQUEST_CODE = 2017
    private val TAG = AlarmActivity::class.java.simpleName
    private var alarmManager: AlarmManager? = null
    private var pendingIntent: PendingIntent? = null

    companion object {
        const val ALARM_DATA = "data"
        const val ACTION_USER_ALARM = "com.example.gooner10.USER_ALARM"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
    }

    fun startAlarmManager(view: View) {
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.action = ACTION_USER_ALARM
        intent.putExtra(ALARM_DATA, "From AlarmActivity")

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

//        val calendar = Calendar.getInstance()
//        calendar.timeInMillis = System.currentTimeMillis()
//        calendar.set(Calendar.HOUR_OF_DAY, 8)
//        calendar.set(Calendar.MINUTE, 30)

        pendingIntent = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val currentTimeInMilliSeconds = SystemClock.elapsedRealtime()
        val SECONDS = 6 * 1000L
        Log.d(TAG, "currentTimeInMilliSeconds: " + currentTimeInMilliSeconds)
        val alarmTime = currentTimeInMilliSeconds + SECONDS
        alarmManager!!.setRepeating(AlarmManager.RTC,
                alarmTime,
                SECONDS
                , pendingIntent)
    }

    fun cancelAlarm(view: View) {
        alarmManager?.cancel(pendingIntent)
    }
}
