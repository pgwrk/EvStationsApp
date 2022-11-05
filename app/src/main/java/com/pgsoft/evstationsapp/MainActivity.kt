package com.pgsoft.evstationsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pgsoft.evstationsapp.ui.theme.EvStationsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvStationsAppTheme {
                AppNavigation()
            }
        }
    }
}