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
import kotlinx.android.synthetic.main.activity_file_content.*
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class FileSizeInBytes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_size_in_bytes)

        var url: String = intent.getStringExtra(Constants.URL_INTENT)

        Log.i("FileSizeInBytes", url)

        var getFile : FileSizeAsync = FileSizeAsync()
        var fileSize : Int? = getFile.execute(url).get()

        tv_display?.text = fileSize.toString()
    }


    class FileSizeAsync : AsyncTask<String, Void, Int>() {

        override fun onPreExecute() {
            super.onPreExecute()

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

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)

        }

        override fun onPostExecute(fileSize: Int) {
            super.onPostExecute(fileSize)

        }
    }
}
