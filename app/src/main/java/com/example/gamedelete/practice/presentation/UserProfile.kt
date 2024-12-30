package com.example.gamedelete.practice.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamedelete.R
import com.example.gamedelete.ui.theme.GameDeleteTheme

@Composable
fun UserProfile() {
    LoginScreen()

}


@Composable
fun LoginScreen() {


    //Background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF345AB9)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(painterResource(R.drawable.facebook_logo), contentDescription = null)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {

            LoginCardWithProfile()

        }

    }


}

@Composable
fun LoginCardWithProfile() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
        //.padding(horizontal = 16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 24.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(6.dp),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(top = 40.dp) // Add padding to leave space for the profile image
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(40.dp))
                Text(
                    text = "Log in to your account",
                    fontSize = 18.sp,
                    color = Color.Black
                )
                // Add other elements like TextFields, Buttons, etc.
            }
        }

        // Profile Picture
        androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.ezlo), // Replace with your image resource
            contentDescription = "Profile Picture",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape) // Make the image circular
                .border(2.dp, Color.White, CircleShape) // Optional: Add a white border
                .align(Alignment.TopCenter)
                .background(MaterialTheme.colorScheme.surface)
        )
    }
}


@Preview
@Composable
private fun UserProfilePreview() {
    GameDeleteTheme {
        UserProfile()
    }
}