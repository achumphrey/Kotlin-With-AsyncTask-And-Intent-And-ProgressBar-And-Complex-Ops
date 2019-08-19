package com.example.kotlinwithasynctaskandintentandprogressbarandcomplexops

import android.app.DownloadManager
import android.app.DownloadManager.*
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_file_content.*
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class FileContent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_content)

        var url: String = intent.getStringExtra(Constants.URL_INTENT)

        Log.i("FileContent", url)

        var fileBody  = DownloadWebPage().execute(url)


    }

    inner class DownloadWebPage : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            prg_bar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg urls: String?): String {

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
                return result

            } catch (e: Exception) {
                return  e.printStackTrace().toString()
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            prg_bar.visibility = View.GONE
            tv_display?.text = result.toString()
        }

    }//END CLASS

}

