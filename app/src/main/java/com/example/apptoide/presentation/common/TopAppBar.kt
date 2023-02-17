package com.example.apptoide.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.apptoide.ui.theme.orange
import com.example.apptoide.ui.theme.orangeLeft
import com.example.apptoide.ui.theme.orangeRight
import com.example.apptoide.ui.theme.white
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AppsTopAppBar() {
    TopAppBar(

        modifier = Modifier.background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    orangeLeft, orangeRight
                )
            )
        ), // TODO: not working for some reason
        backgroundColor = orange,

        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {
                Text(
                    "Aptoide",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    textAlign = TextAlign.Center,
                    color = white, //contrastTitle works for accessibility, looks awful
                    fontSize = 24.sp,
//                    color = contrastTitle
//                    backgroundColor = Color.Transparent
                )
            }
        },
    )
}