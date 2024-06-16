package com.semeprojects.budgetbuddy.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.semeprojects.budgetbuddy.ui.components.BButton
import com.semeprojects.budgetbuddy.ui.components.BText
import com.semeprojects.budgetbuddy.ui.nav_utils.BudgetBuddyScreens
import com.semeprojects.budgetbuddy.viewmodel.WelcomeViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navController: NavHostController
){

    val welcomeViewModel = hiltViewModel<WelcomeViewModel>()

    val pages = listOf(
        OnBoardingScreen.First,
        OnBoardingScreen.Second,
        OnBoardingScreen.Third
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        pages.size
    }

    Column(modifier = Modifier
        .background(
            color = MaterialTheme.colorScheme.background
        ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            pageContent = { position ->
                PagerScreen(screen = pages[position])
            },
            modifier = Modifier.weight(9f)
        )
        Column(
            modifier = Modifier.weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = 3,
                activeColor = MaterialTheme.colorScheme.primary,
                inactiveColor = Color.Gray,
                indicatorShape = RoundedCornerShape(50f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            GetStartedButton(
                pagerState = pagerState, onClick = {
                    welcomeViewModel.saveOnBoardingState(completed = true)
                    navController.popBackStack()
                       navController.navigate(BudgetBuddyScreens.Home.route)
                },
                modifier = Modifier             .padding(bottom = 15.dp)
            )
        }
    }
}


@Composable
fun PagerScreen(screen : OnBoardingScreen){

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f),
                    painter = painterResource(id = screen.image), contentDescription = "")

                Spacer(modifier = Modifier.height(15.dp))
                BText(text = "Budget Buddy",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    textAlign = TextAlign.Center,
                    size = 30.sp
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ){
                BText(text = screen.title,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    size = 20.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                BText(text = screen.description,
                    size = 14.sp,
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    textAlign = TextAlign.Center)
            }
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GetStartedButton(
    pagerState: PagerState,
    onClick: ()-> Unit,
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        AnimatedVisibility(visible = pagerState.currentPage == 2) {
            BButton(
                text = "Continue",
                click = onClick
            )
        }
    }

}


//@Composable
//fun HomeScreen(navController: NavHostController){
//    PText(text = "Home Screen")
//}
