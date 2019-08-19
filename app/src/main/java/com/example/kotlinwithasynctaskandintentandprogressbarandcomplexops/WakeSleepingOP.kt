package com.example.kotlinwithasynctaskandintentandprogressbarandcomplexops

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_file_content.*

class WakeSleepingOP : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wake_sleeping_op)

        var url: String = intent.getStringExtra(Constants.URL_INTENT)
        tv_display?.text = url

        Log.i("FileContent", url)

        var makeItSleep : MakeToSleep = MakeToSleep()
        var message : String? = makeItSleep.get()

        tv_display?.text = message
    }

    private class MakeToSleep : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg p0: Void?): String {

            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            return "Sleeping......."
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

        }


    }
}
