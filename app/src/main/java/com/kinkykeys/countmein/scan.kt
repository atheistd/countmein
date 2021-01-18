package com.kinkykeys.countmein

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator


class scan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan)

        // THE BELOW CODE WAS/IS INTENDED AS A PLACEHOLDER WHEN THERE IS NO SCAN VIEW
        // val temp = findViewById(R.id.scan_temp) as TextView
        // temp.setText("This view will open a camera for scanning the QR Code once the module is updated.")

        val scanner = IntentIntegrator(this)
        scanner.initiateScan()
    }
}