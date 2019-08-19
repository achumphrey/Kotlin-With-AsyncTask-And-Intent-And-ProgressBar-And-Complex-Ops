package com.example.kotlinwithasynctaskandintentandprogressbarandcomplexops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(view: View?) {

        var url: String = ed_enter_valid_url.text.toString()
        var intent: Intent? = null

        if (url.isEmpty()){

            tv_error_display.text = "Please enter a valid Url"

        }else{

            when (view!!.id){

                R.id.btn_Wake_a_sleeping_operation -> {
                   intent = Intent(this, WakeSleepingOP::class.java)
                }

                R.id.btn_get_file_content -> {
                    intent = Intent(this, FileContent::class.java)
                }

                R.id.btn_get_file_size -> {
                    intent = Intent(this, FileSizeInBytes::class.java)
                }

                R.id.btn_random_factorial->{
                    intent = Intent(this, RandomNumberFactorial::class.java)
                }

            }//END WHEN

            intent!!.putExtra(Constants.URL_INTENT, url)
            startActivity(intent)

        }//END ELSE

    }//END ONCLICK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_Wake_a_sleeping_operation.setOnClickListener(this)
        btn_get_file_content.setOnClickListener(this)
        btn_get_file_size.setOnClickListener(this)
        btn_random_factorial.setOnClickListener(this)
    }
}
