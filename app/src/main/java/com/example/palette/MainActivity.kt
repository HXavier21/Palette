package com.example.palette

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.palette.ui.theme.PaletteTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaletteTheme {
                PaletteScreen()
            }
        }
    }

    companion object {
        init {
            System.loadLibrary("palette")

        }
    }
}

object HCT {
    external fun hct(hue: Double, chroma: Double, tone: Double): Int

    fun setHct(hue: Double, chroma: Double, tone: Double): Int {
        return hct(hue, chroma, tone)
    }
}