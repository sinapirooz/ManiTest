package com.example.manitest.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manitest.ui.theme.headerColor
import com.example.manitest.ui.theme.indicatorColor
import com.example.manitest.ui.theme.onBackgroundColor

@Composable
fun Header(
    modifier: Modifier,
    title: String
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Row(Modifier.background(headerColor)) {
            Row(Modifier.padding(horizontal = 12.dp, vertical = 16.dp)) {
                Text(
                    text = title,
                    color = onBackgroundColor,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ),
                    fontSize = 18.sp
                )
                Spacer(modifier = modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.Menu,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = null,
                    tint = onBackgroundColor
                )
            }
        }
    }
}

@Composable
fun ErrorMessage(color: Color) {

    Box(Modifier.fillMaxSize()) {
        Text(
            text = "Error in fetching data",
            color = color,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
        )

    }

}

@Composable
fun Loading() {

    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.Center), color = indicatorColor)
    }

}