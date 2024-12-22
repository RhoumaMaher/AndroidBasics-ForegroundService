package com.rhouma.androidbasicsforegroundservices

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.rhouma.androidbasicsforegroundservices.ui.theme.AndroidBasicsForegroundServicesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                0
            )
        }

        enableEdgeToEdge()
        setContent {
            AndroidBasicsForegroundServicesTheme {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        Intent(applicationContext, TestService::class.java).also {
                            it.action = TestService.Action.START.toString()
                            startService(it)
                        }
                    }) {
                        Text(text = "Start Service")
                    }
                    Button(onClick = {
                        Intent(applicationContext, TestService::class.java).also {
                            it.action = TestService.Action.STOP.toString()
                            startService(it)
                        }
                    }) {
                        Text(text = "Stop Service")
                    }
                }

            }
        }
    }
}