package com.example.gamedelete.NameList.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun NameListRoot(viewModel: NameListViewModel) {

    NameList(
        viewModel.state,
        viewModel::onNameChanged,
        viewModel::addName,
        viewModel::deleteName
    )

}


@Composable
fun NameList(
    state: NameState,
    onNameChanged: (String) -> Unit,
    onButtonClick : () -> Unit,
    onRowClick : (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            ) {

            TextField(
                value = state.name,
                onValueChange = onNameChanged,
                placeholder = { Text("Name") },
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.padding(4.dp))

            Button(
                onClick = onButtonClick
            ) {
                Text("Add")
            }


        }

        LazyColumn(
            Modifier.fillMaxSize()
        ) {

            items(items = state.names) { name ->

                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 16.dp),
                    )

                    Button(
                        onClick = { onRowClick(name) },
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }


                }
                HorizontalDivider()


            }

        }
    }


}


@Preview
@Composable
private fun NameListPreview() {

    NameList(
        NameState(
            names = listOf("John", "Joe", "Mary")
        ),
        onNameChanged = {},
        onButtonClick = {},
        onRowClick = {}
    )

}