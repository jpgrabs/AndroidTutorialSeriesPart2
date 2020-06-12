package com.techgenus.androidstudiotutorialseriespart2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view_tutorial.*

class WebViewTutorial : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_tutorial)
        //Welcome back again to my YouTube Android Tutorial Series
        //Today lessons is how to insert URL in webview
        //Thanks for watching
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.google.com")

    }
}
