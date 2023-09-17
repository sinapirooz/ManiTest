package com.example.manitest.ui.activity

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.manitest.ui.screen.NavGraphs
import com.example.manitest.ui.theme.ManiTestTheme
import com.example.manitest.ui.theme.backgroundColor
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @SuppressLint("SourceLockedOrientationActivity")
        requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {

            ManiTestTheme {
                Box(
                    modifier = Modifier.fillMaxSize().background(backgroundColor),
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}