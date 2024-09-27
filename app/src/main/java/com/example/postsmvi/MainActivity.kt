package com.example.postsmvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.postsmvi.ui.PostsScreen
import com.example.postsmvi.ui.theme.PostsMVITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostsMVITheme {
                Surface {
                    PostsScreen()
                }
            }
        }
    }
}