package com.example.gamedelete.delete_package

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

val lista = listOf(
    "ola",
    "ola","ola","ola",
    "ola","ola","ola","ola","ola","ola",
    "ola","ola","ola","ola","ola",
    "ola","ola","ola","ola","ola",
)

@Composable
fun DeleteScreenRoot(viewModel: DeleteViewModel) {
    DeleteScreen(
        viewModel.state,
        onSelectItem = viewModel::changeIndex
    )
}

@Composable
fun DeleteScreen(
    state : DeleteState,
    onSelectItem : (Int) -> Unit
) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(lista){index, text ->
            Text(text = text,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .background( if (state.selectedIndex == index) Color.Blue else Color.White)
                    .clickable { onSelectItem(index) }
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun DeleteScreenPreview() {
    DeleteScreen(DeleteState(), {})
}