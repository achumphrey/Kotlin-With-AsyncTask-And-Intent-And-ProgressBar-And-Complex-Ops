package com.example.kotlinwithasynctaskandintentandprogressbarandcomplexops

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_wake_sleeping_op.*


class WakeSleepingOP : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wake_sleeping_op)

        var url: String = intent.getStringExtra(Constants.URL_INTENT)
        Log.i("FileContent", url)
        var message  = MakeToSleep().execute()
    }

    inner class MakeToSleep : AsyncTask<Void, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            tv_display?.text = "Going to sleep for 5 seconds"
            prg_bar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg p0: Void?): String {

            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            return "Just woke after sleeping for 5 seconds"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            prg_bar.visibility = View.GONE
            tv_display?.text = result.toString()
        }
    }
}
