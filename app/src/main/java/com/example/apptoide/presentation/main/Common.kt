package com.example.apptoide.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptoide.R
import com.example.apptoide.presentation.common.AppsTopAppBar
import com.example.apptoide.ui.theme.orange
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigator : DestinationsNavigator
) {

    Scaffold(
        topBar = {
            AppsTopAppBar()
        },
        content = {
            ScafContent(it, navigator = navigator)
        },
    )
}

@Destination
@Composable
fun ScafContent(paddingValues: PaddingValues, navigator: DestinationsNavigator) {
    Column(modifier = Modifier.padding(paddingValues)) {

        CatHeader("Editors Choice")
        ToDisplayTop(navigator = navigator)
        Spacer(modifier = Modifier.height(10.dp))
        CatHeader("Local top apps")
        ToDisplayBottom(navigator = navigator)
    }
}



@Destination
@Composable
fun CatHeader(name: String = "name") {
    Row(
        Modifier
            .fillMaxWidth(1f)
            .padding(12.dp, 20.dp)
    ) {
        Text(
            text = name,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(0.85f),
            fontSize = 18.sp
        )
        Text(
            text = "More in $name",
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            color = orange, //  can't find a suitable orange for accessibility to be fine
            textAlign = TextAlign.End,
            modifier = Modifier.clickable(onClickLabel = stringResource(R.string.action_open_more) + name) { },
        )
    }
}

@Destination
@Composable
fun ShowErrorMessage(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, style = MaterialTheme.typography.displayMedium)
    }
}

@Destination
@Composable
fun ShowLoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

