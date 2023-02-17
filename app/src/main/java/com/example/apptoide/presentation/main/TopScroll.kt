package com.example.apptoide.presentation.main

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apptoide.R
import com.example.apptoide.data.structure.AppInf
import com.example.apptoide.destinations.DetailsScreenDestination
import com.example.apptoide.domain.ViewModelATI
import com.example.apptoide.ui.theme.white
import com.example.apptoide.utils.Resource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage


@Destination
@Composable
fun ToDisplayTop(
    viewModel: ViewModelATI = viewModel(LocalContext.current as ComponentActivity),
    navigator: DestinationsNavigator
) {
    val state by viewModel.itemsList.collectAsState()
    val itemList = state.data?.responses?.listApps?.datasets?.all?.data?.list

    Log.i("DebugNetworkJustMain", itemList.toString())
    when (state) {
        is Resource.Success -> {

            itemList?.let {
                TopScroll(listItemList = itemList, navigator = navigator, state = viewModel.topState)
                Log.i("DebugNetworkInLet", itemList.toString())
            }
        }
        is Resource.Loading -> {
            ShowLoadingIndicator()
            Log.i("DebugNetworkMainLoading", "Loading")
        }
        is Resource.Error -> {
            Log.i("DebugNetworkMainError", "Error")
            state.message?.let { ShowErrorMessage(message = it) }
        }
    }
}


@Destination
@Composable
fun TopScroll(listItemList: List<AppInf>?, navigator: DestinationsNavigator, state : LazyListState) {
    val list = listItemList!!.asReversed()
    Log.i("DebugNetworkMain", list.toString())
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(12.dp, 0.dp),
        state = state
    ) {
        items(list) {
            TopCardItem(
                list = it,
                navigator = navigator,
            )
        }
    }
}

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopCardItem(
    list: AppInf,
    navigator: DestinationsNavigator,
) {
    val name = list.name
    val downloads = list.downloads
    val size = list.size
    val rating = list.rating
    val version = list.vercode
    val icon = list.icon

    val readCardLabel = stringResource(id = R.string.action_read_card)
    Card(
        modifier = Modifier
            .size(width = 350.dp, height = 225.dp)
            .clickable(onClickLabel = stringResource(R.string.action_open_details) + list.name) { }
            .semantics { onClick(label = readCardLabel + list.name, action = null) },
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(16.dp),
        onClick = {
            navigator.navigate(
                DetailsScreenDestination.invoke(
                    name = "$name",
                    downloads = downloads!!,
                    size = size!!,
                    rating = rating!!,
                    version = "$version",
                    icon = icon!!
                )
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            GlideImage(
                imageModel = list.graphic, //todo .graphic causes crashes, or not after passing a bundle, need to test
                contentDescription = "image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            ), startY = 400f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(
                        text = "${list.name}",
                        style = TextStyle(color = Color.White, fontSize = 18.sp, FontWeight.Bold),
                        maxLines = 1,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star",
                            Modifier.size(10.dp),
                            tint = white
                        )
                        Text(
                            text = "${list.rating}",
                            style = TextStyle(color = Color.White, fontSize = 12.sp)
                        )
                    }

                }
            }
        }
    }
}

