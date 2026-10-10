package com.example.battlerhythm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.battlerhythm.ui.RhythmScreen
import com.example.battlerhythm.ui.theme.BattleRhythmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BattleRhythmTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RhythmScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}