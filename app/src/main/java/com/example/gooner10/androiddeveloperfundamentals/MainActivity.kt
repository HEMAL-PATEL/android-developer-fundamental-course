package com.example.gooner10.androiddeveloperfundamentals

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

const val TEXT_REQUEST = 1
private val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * When the button is clicked, it shows toast
     */
    fun showToast(view: View) {
        Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ScrollActivity::class.java)
        startActivityForResult(intent, TEXT_REQUEST)
    }

    /**
     * Increases the count in text, when the button is clicked
     */
    fun countUp(view: View) {
        countTextView.text = Integer.toString(count++)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "requestCode " + requestCode)
        Log.d(TAG, "resultCode " + resultCode)
        Log.d(TAG, "intent " + data)
        if (requestCode == TEXT_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Log.d(TAG, "intent " + data.getStringExtra(REPLY))
                toastButton.text = data.getStringExtra(REPLY)
            }
        }
    }
}
