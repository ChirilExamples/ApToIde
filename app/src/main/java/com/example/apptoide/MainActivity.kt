package com.example.apptoide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import com.example.apptoide.data.structure.AppInf
import com.example.apptoide.destinations.DetailsScreenDestination
import com.example.apptoide.ui.theme.AppToIdeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppToIdeTheme {

                DestinationsNavHost(navGraph = NavGraphs.root)


//                Surface(
//                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
//                ) {
//
////                    MainScreen()
//                    DetailsScreen()
//                }
            }
        }
    }
}

@RootNavGraph(start = false)
@Destination
@Composable
fun RandomButton(navigator: DestinationsNavigator) {
//    Button(onClick = { navigator.navigate(DetailsScreenDestination()) }) {
//        AppInf(
//            1,
//            "https://pool.img.aptoide.com/appupdater/aa7a77f7744a621751f8c09c6b3437d1_fgraphic.png",
//            "https://pool.img.aptoide.com/appupdater/aa7a77f7744a621751f8c09c6b3437d1_fgraphic.png",
//            "namssssa",
//            0.3,
//            100,
//            620
//        )
//    }
}

//        AppInf(
//            1,
//            "https://pool.img.aptoide.com/appupdater/aa7a77f7744a621751f8c09c6b3437d1_fgraphic.png",
//            "https://pool.img.aptoide.com/appupdater/aa7a77f7744a621751f8c09c6b3437d1_fgraphic.png",
//            "nama",
//            0.3,
//            100,
//            620
//        )

