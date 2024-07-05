package com.example.notesdrive.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notesdrive.data.Note
import com.example.notesdrive.navigation.Screen
import com.example.notesdrive.view.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: NoteViewModel,
    navController: NavHostController
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
                    navController.navigate(route = Screen.AddScreen.route)
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
            Row {
                Column(
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Всего: ",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = SumAllRepair(notes)
                            .toString() + " р.",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error
                        )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ){
                items(notes) { n ->
                    NoteCard(
                        n,
                        delete = { viewModel.deleteNote(it) },
                        navController
                    )
                }
            }
        }
    }
}

fun SumOfColumnRefill(notes: List<Note>) : Int {
    var sum = 0
    for (item in notes) {
        if (item.costType == "Заправка") sum += item.cost
    }
    return sum
}

fun SumOfColumnPart(notes: List<Note>) : Int {
    var sum = 0
    for (item in notes) {
        if (item.costType == "Запчасти") sum += item.cost
    }
    return sum
}

fun SumOfColumnRepair(notes: List<Note>) : Int {
    var sum = 0
    for (item in notes) {
        if (item.costType == "Ремонт") sum += item.cost
    }
    return sum
}

fun SumAllRepair(notes: List<Note>) : Int {
    var sum = 0
    for (item in notes) {
        sum += item.cost
    }
    return sum
}