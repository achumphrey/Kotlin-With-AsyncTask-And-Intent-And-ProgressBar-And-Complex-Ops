package com.example.kotlinwithasynctaskandintentandprogressbarandcomplexops
import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_file_size_in_bytes.*
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class FileSizeInBytes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_size_in_bytes)

        var url: String = intent.getStringExtra(Constants.URL_INTENT)

        Log.i("FileSizeInBytes", url)

        var getFile = FileSizeAsync().execute(url) // do not require the get method here
    }

    //make this an inner class so you can access the class context
    inner class FileSizeAsync : AsyncTask<String, Void, Int>() {

        override fun onPreExecute() {
            super.onPreExecute()
            prg_bar.visibility = View.VISIBLE //make the progress bar visible
                                            //while asynctask code runs
        }

        override fun doInBackground(vararg urls: String?): Int? {

            var result = ""
            val url: URL
            var urlConnection: HttpURLConnection? = null

            try {
                url = URL(urls[0])
                urlConnection = url.openConnection() as HttpURLConnection
                val input = urlConnection.inputStream
                val reader = InputStreamReader(input)
                var data = reader.read()

                while (data != -1) {
                    val current = data.toChar() //converts from number to letter
                    result += current
                    data = reader.read()
                }
                var fileSize : Int? = result?.length
                return fileSize

            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
        }

        override fun onPostExecute(fileSize: Int) {
            super.onPostExecute(fileSize)
            prg_bar.visibility = View.GONE //progress bar to disappear once
                                            //asynctask is finished running
            tv_display?.text = "$fileSize Bytes"
        }
    }
}
