package com.example.notesdrive.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesdrive.data.Note
import com.example.notesdrive.view.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: NoteViewModel,
    onClick: () -> Unit
) {
    val notes by viewModel.loadAllByDateAdded.observeAsState(initial = listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(text = "Затраты на авто:")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onClick()
                },
                containerColor = MaterialTheme.colorScheme.primary,
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Заправка: " + SumOfColumnRefill(notes).toString() + " р.",
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Запчасти: " + SumOfColumnPart(notes).toString() + " р.",
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Ремонт: " + SumOfColumnRepair(notes).toString() + " р.",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ){
                items(notes) { n ->
                    NoteCard(
                        n,
                        delete = { viewModel.deleteNote(it) }
                    )
                }
            }
        }
    }
}

fun SumOfColumnRefill(notes: List<Note>) : Int {
    var sum = 0
    for (item in notes) {
        if (item.costType == "Заправка" && item.cost != null) sum += item.cost
    }
    return sum
}

fun SumOfColumnPart(notes: List<Note>) : Int {
    var sum = 0
    for (item in notes) {
        if (item.costType == "Запчасти" && item.cost != null) sum += item.cost
    }
    return sum
}

fun SumOfColumnRepair(notes: List<Note>) : Int {
    var sum = 0
    for (item in notes) {
        if (item.costType == "Ремонт" && item.cost != null) sum += item.cost
    }
    return sum
}