package com.dicoding.mdminsatuapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dicoding.mdminsatuapp.util.listData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.ui.components.CustomOutlinedButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val (selectedPage,setSelectedPage) = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(pagerState){
        snapshotFlow { pagerState.currentPage }.collect{ page ->
            setSelectedPage(page)
        }
    }

    Scaffold{
        Column(Modifier.fillMaxSize()) {
            HorizontalPager(
                count = listData.size,
                state = pagerState ,
                modifier = Modifier.weight(0.6f)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = listData[it].image),
                        contentDescription = "img"
                    )
                    Text(
                        text = listData[it].title,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = listData[it].description,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(8.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        for (i in listData.indices){
                            Box(
                                modifier = Modifier
                                    .padding(end = if (i == listData.size - 1) 0.dp else 4.dp)
                                    .width(if (i == selectedPage) 60.dp else 30.dp)
                                    .height(3.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        if (i == selectedPage) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onSurface.copy(
                                            alpha = 0.1f
                                        )
                                    )
                            )
                        }

                    }
                }
            }

            if(selectedPage != listData.size-1) {
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                ) {
                    CustomOutlinedButton(
                        text = "Next",
                        onClick = {
                            scope.launch {
                                val nextpage = selectedPage + 1
                                pagerState.animateScrollToPage(nextpage)
                            }
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp),



                    ) )
                }
            } else {
                CustomOutlinedButton(
                    onClick = {
                        navController.navigate("login")
                    },
                    text = "Get Started",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(36.dp)
                        .clip(RoundedCornerShape(16.dp))

                )
            }
        }
    }
}
