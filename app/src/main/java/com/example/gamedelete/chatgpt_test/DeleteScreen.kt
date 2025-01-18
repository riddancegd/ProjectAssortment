package com.example.gamedelete.chatgpt_test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamedelete.R
import com.example.gamedelete.ui.theme.GameDeleteTheme

@Composable
fun OnboardingScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Page(
            modifier = Modifier.weight(10f)
        )

        // This spacer pushes the indicator down from the page
        Spacer(modifier = Modifier.weight(1f))

        PagerIndicator(
            modifier = Modifier.padding(vertical = 24.dp)
        )

        // This spacer pushes the button down from the indicator
        Spacer(modifier = Modifier.weight(1f))

        OnboardingButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        )

    }


}

@Composable
fun OnboardingButton(modifier: Modifier = Modifier) {

    Button(
        modifier = modifier,
        onClick = {},
    ) {
        Text("Continue")
    }

}

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        repeat(3) {

            val color = if (it == 0) Color.Black else Color.Black.copy(alpha = 0.5f)

            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }

    }


}

@Composable
fun Page(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Onboarding picture"
        )

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Onboarding",
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))
        Text(
            text = "Lorem ipsum dolor sit amet, " +
                    "consectetur adipiscing elit. Sed " +
                    "do eiusmod tempor incididunt ut labore " +
                    "et dolore magna aliqua.",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )


    }

}

@Preview(showSystemUi = true)
@Composable
private fun UserProfileScreenPreview() {

    GameDeleteTheme {
        OnboardingScreen()

    }

}