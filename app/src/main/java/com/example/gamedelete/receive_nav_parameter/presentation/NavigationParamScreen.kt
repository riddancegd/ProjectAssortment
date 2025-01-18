package com.example.gamedelete.receive_nav_parameter.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamedelete.ui.theme.GameDeleteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationParamScreen(
    param: String,
    state: NavigatorParamState,
    onAction : (NavigatorParamActions) -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {

            FloatingActionButton(onClick = { onAction(NavigatorParamActions.OnNavigateBackPressed) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back button"
                )
            }

        },
        topBar = {
            TopAppBar(
                title = {

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        //verticalAlignment = Alignment.CenterVertically
                    ){

                        Text("Param is: $param")

                    }

                },
                modifier = Modifier.shadow(16.dp)
            )
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.shadow(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround, // Evenly distribute the icons
                    verticalAlignment = Alignment.CenterVertically // Align items vertically (optional)
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home menu button"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Calendar button menu"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Build,
                            contentDescription = "Settings button menu"
                        )
                    }
                }
            }
        }

    ) {

        Column(
            modifier = Modifier
                .padding(it)
        ) {

            FilterTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                value = state.filterValue,
                onValueChange = { onAction(NavigatorParamActions
                    .OnFilterValueChange(it)) }
            )


            ListOfNames(
                nameList = state.names,
                modifier = Modifier,
                filterValue = state.filterValue
            )

        }

    }


}

@Composable
fun ListOfNames(
    modifier: Modifier,
    nameList : List<String>,
    filterValue : String
)
{
    // Filter the list before passing it to ListOfNames
    val filteredNames = remember(nameList, filterValue) {
        nameList.filter { it.contains(filterValue, ignoreCase = true) }
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        items(filteredNames){ name ->

            NameItem(
                name,
                modifier = Modifier.fillMaxWidth()
            )

        }

    }
}

@Composable
fun FilterTextField(
    modifier: Modifier = Modifier,
    value : String,
    onValueChange : (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        label = { Text("Filter") },
        leadingIcon = { Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = ""
        ) }

    )
}

@Composable
fun NameItem(name : String, modifier: Modifier) {

    OutlinedCard(
        modifier = modifier
    ) {
        Text(text = name, modifier = Modifier.padding(16.dp))
    }

}




@Preview(showSystemUi = true)
@Composable
private fun NavigationParamScreenPreview() {
    GameDeleteTheme {
        NavigationParamScreen(
            "Hello",
            state = NavigatorParamState(
                names = listOf("Juan", "Pedro", "Luis"),
            ),
            onAction = {},
        )
    }
}