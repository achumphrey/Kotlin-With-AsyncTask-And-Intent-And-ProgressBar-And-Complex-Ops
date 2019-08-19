package com.example.kotlinwithasynctaskandintentandprogressbarandcomplexops

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_file_content.*
import kotlinx.android.synthetic.main.activity_file_content.prg_bar
import kotlinx.android.synthetic.main.activity_file_content.tv_display
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_random_number_factorial.*
import kotlin.random.Random

class RandomNumberFactorial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number_factorial)

        var url: String = intent.getStringExtra(Constants.URL_INTENT)
        Log.i("FileContent", url)
        var result  = RandomNumberFactorial().execute()
    }

    inner class RandomNumberFactorial : AsyncTask<Void, Void, Long>() {

        val rand = Random
        var num: Int = rand.nextInt(20) + 1

        override fun onPreExecute() {
            super.onPreExecute()
            prg_bar.visibility = View.VISIBLE

        }

        override fun doInBackground(vararg p0: Void?): Long {
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            var result: Long = naiveFact(num)
            return result

            }


        override fun onPostExecute(result: Long?) {
            super.onPostExecute(result)
            prg_bar.visibility = View.GONE
            tv_display.text = result.toString()
        }

        fun naiveFact(num: Int): Long {

            var nFactorial: Long
            if (num <= 1) {
                return 1
            } else {
                nFactorial = num * naiveFact(num - 1)
                return nFactorial
            }
        }
    }
}
