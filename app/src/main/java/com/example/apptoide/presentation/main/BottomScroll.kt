package com.example.apptoide.presentation.main

import android.util.Log
import androidx.activity.ComponentActivity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apptoide.R
import com.example.apptoide.data.structure.AppInf
import com.example.apptoide.destinations.DetailsScreenDestination
import com.example.apptoide.domain.ViewModelATI
import com.example.apptoide.utils.Resource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage

@Destination
@Composable
fun ToDisplayBottom(viewModel: ViewModelATI = viewModel(LocalContext.current as ComponentActivity), navigator: DestinationsNavigator) {
    val state by viewModel.itemsList.collectAsState()
    val itemList = state.data?.responses?.listApps?.datasets?.all?.data?.list

    Log.i("DebugNetworkJustMain", itemList.toString())
    when (state) {
        is Resource.Success -> {

            itemList?.let {
                BottomScroll(listItemList = itemList, navigator = navigator, state = viewModel.bottomBarState)
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
fun BottomScroll(listItemList: List<AppInf>?, navigator: DestinationsNavigator, state : LazyListState) {
    val list = listItemList!!.asReversed()
    Log.i("DebugNetworkMain", list.toString())
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(12.dp, 0.dp),
        state = state
    ) {
        items(list) {
            BottomCardItem(
                list = it,
                navigator = navigator,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun BottomCardItem(
    list: AppInf,
    navigator: DestinationsNavigator
) {
    val name = list.name
    val downloads = list.downloads
    val size = list.size
    val rating = list.rating
    val version = list.vercode
    val icon = list.icon
    Card(
        modifier = Modifier
            .width(100.dp)
            .clickable(onClickLabel = stringResource(R.string.action_open_details) + name) { },
        shape = RoundedCornerShape(5.dp), elevation = CardDefaults.cardElevation(16.dp),
        onClick = { navigator.navigate(DetailsScreenDestination.invoke(
            name = "$name",
            downloads = downloads!!,
            size = size!!,
            rating = rating!!,
            version = "$version",
            icon = icon!!
        )) }
    ) {
        Column {
            Card(shape = RoundedCornerShape(6.dp), modifier = Modifier.padding(3.dp)) {
                GlideImage(
                    imageModel = icon,
                    contentDescription = "image",
                    modifier = Modifier.size(94.dp)
                )
            }
            Text(
                text = "$name",
                maxLines = 1,
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 3.dp, start = 4.dp, end = 4.dp),
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star",
                    Modifier.size(6.dp)
                )
                Text(
                    text = "$rating",
                    maxLines = 1,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(start = 2.dp, end = 4.dp)
                        .wrapContentHeight(),
                    textAlign = TextAlign.Center,
                )

            }
        }
    }
}
