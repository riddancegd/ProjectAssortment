package com.example.gamedelete.onboarding.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamedelete.R
import com.example.gamedelete.ui.theme.GameDeleteTheme
import kotlinx.coroutines.launch

data class OnboardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)

@Composable
fun OnboardingScreen(onFinishOnboarding: () -> Unit) {
    val pages = listOf(
        OnboardingPage(
            image = R.drawable.ic_launcher_background,
            title = "Welcome!",
            description = "Description for first screen"
        ),
        OnboardingPage(
            image = R.drawable.facebook_logo,
            title = "Easy to Use",
            description = "Description for second screen"
        ),
        OnboardingPage(
            image = R.drawable.ezlo,
            title = "Get Started",
            description = "Description for third screen"
        )
    )

    val pagerState = rememberPagerState { pages.size }

    // La composición se organiza en un Box, con controles y contenido del Pager separados
    Box(modifier = Modifier.fillMaxSize()) {
        // Pager con las páginas del onboarding
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { position ->
            OnboardingPage(page = pages[position]) // Uso de componente para cada página
        }

        // Controles (indicadores y botones) manejados en una función separada
        OnboardingControls(
            pagerState = pagerState,
            pageCount = pages.size,
            onFinishOnboarding = onFinishOnboarding

        )
    }
}

@Composable
fun OnboardingControls(
    pagerState: PagerState,
    pageCount: Int,
    onFinishOnboarding: () -> Unit
) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Indicators (Dots)
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter), // Align dots to the bottom center
            //.padding(bottom = 16.dp), // Add space from the bottom
            horizontalArrangement = Arrangement.Center // Center the dots
        ) {
            repeat(pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp) // Space between dots
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp) // Uniform size for dots
                )
            }
        }

        // Dynamic Button
        Button(
            onClick = {
                if (pagerState.currentPage == pageCount - 1) {
                    onFinishOnboarding()
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd) // Align button to the bottom end
                .padding(16.dp) // Add space from the bottom and right
        ) {
            Text(
                text = if (pagerState.currentPage == pageCount - 1) "Finish" else "Continue"
            )
        }
    }
}


@Composable
fun OnboardingPage(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = page.title,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun OnboardingScreenPreview() {

    GameDeleteTheme {
        OnboardingScreen {}
    }

}