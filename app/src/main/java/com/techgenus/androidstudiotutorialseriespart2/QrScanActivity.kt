package com.techgenus.androidstudiotutorialseriespart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_qr_scan.*
import org.json.JSONException
import org.json.JSONObject

class QrScanActivity : AppCompatActivity() {

    private var qrScan: IntentIntegrator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scan)

        qrScan = IntentIntegrator(this)

        btnQrScan.setOnClickListener {
            qrScan!!.initiateScan()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       var result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)

        if(result!=null){
            //if qr code has nothing in it
            if (result.contents ==null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show()
            }else {
                //if qr code contain data
                try {
                    //converting the data to json
                    val obj = JSONObject(result.contents)
                    Toast.makeText(this, "Result:" + result.contents, Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    //if control comes here that means the encoded format not matches
                    //in this case you can display whatever data is available on the qr code
                    Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

}
