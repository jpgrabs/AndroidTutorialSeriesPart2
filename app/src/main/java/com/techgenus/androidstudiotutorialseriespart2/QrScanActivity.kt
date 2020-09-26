package com.techgenus.androidstudiotutorialseriespart2

import android.accounts.AccountManager
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_qr_scan.*
import kotlinx.android.synthetic.main.activity_spinner_tutorial.*
import org.json.JSONException
import org.json.JSONObject

class QrScanActivity : AppCompatActivity() {

    private var qrScan: IntentIntegrator? = null
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scan)

        qrScan = IntentIntegrator(this)

        btnQrScan.setOnClickListener {
            qrScan!!.initiateScan()
        }
        btnFingerPrintScan.setOnClickListener{
            showBiometricPrompt()
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

    @RequiresApi(Build.VERSION_CODES.P)
    private fun showBiometricPrompt(){
        //Check if the device support Biometric Prompt API
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            val prompt = BiometricPrompt.Builder(this)
                .setTitle("Authentication")
                .setDescription("Use your Fingerprint to login")
                .setNegativeButton("Cancel",
                    mainExecutor,DialogInterface.OnClickListener{_,_->})
                .build()

            prompt.authenticate(getCancellationSignal(),mainExecutor,
                @RequiresApi(Build.VERSION_CODES.P)
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(
                        result: BiometricPrompt.AuthenticationResult?) {
                        super.onAuthenticationSucceeded(result)
                        //Action success display here
                        fingerPrintAction()
                    }

                })
        }


    }

    fun fingerPrintAction(){
        Toast.makeText(this,"Success Fingerprint scan", Toast.LENGTH_LONG).show()
        val intent= Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    private fun getCancellationSignal():CancellationSignal{
        //With this Cancel signal, we can cancel biometric prompt operation
        val cancellationSignal = CancellationSignal()
        cancellationSignal.setOnCancelListener {
            //handle result action if you want here
        }
        return cancellationSignal
    }
}
