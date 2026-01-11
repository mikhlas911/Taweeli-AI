package com.example.taweeli_app

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.taweeli_app.services.VoiceAgentService
import com.example.taweeli_app.ui.theme.Taweeli_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Taweeli_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Greeting(
                            name = "Android"
                        )
                        AccessibilityStatus()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun AccessibilityStatus() {
    val context = LocalContext.current
    Button(onClick = {
        val accessibilityEnabled = isAccessibilityServiceEnabled(context)
        Toast.makeText(context,
            if (accessibilityEnabled) "✅ Accessibility OK" else "❌ Enable Accessibility",
            Toast.LENGTH_LONG).show()
    }) {
        Text("Test Accessibility")
    }
}

private fun isAccessibilityServiceEnabled(context: Context): Boolean {
    val service = ComponentName(context, VoiceAgentService::class.java)
    val enabledServices = Settings.Secure.getString(
        context.contentResolver,
        Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
    )
    return enabledServices?.contains(service.flattenToString()) == true
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Taweeli_AppTheme {
        Greeting("Android")
    }
}