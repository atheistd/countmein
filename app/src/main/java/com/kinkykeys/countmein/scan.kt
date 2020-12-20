package com.kinkykeys.countmein

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class scan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan)

        val temp = findViewById(R.id.scan_temp) as TextView
        temp.setText("This view will open a camera for scanning the QR Code once the module is updated.")
    }
}