package com.example.notesdrive.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.RoomDatabase
import com.example.notesdrive.data.Note
import com.example.notesdrive.view.NoteViewModel
import java.util.Date

@Composable
fun MainScreen(
    viewModel: NoteViewModel
) {
    val countRow by viewModel.countRow.observeAsState(initial = Int)
    val notes by viewModel.loadAllByDateAdded.observeAsState(initial = listOf())

    var noteTitle by remember {
        mutableStateOf("")
    }
    var noteDescription by remember {
        mutableStateOf("")
    }
    var noteCost by remember {
        mutableIntStateOf(0)
    }



    val note = Note(
        title = noteTitle,
        description = noteDescription,
        dateAdded = Date().time,
        cost = noteCost
    )
    Scaffold(

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Row {
                Text(text = CountRow(notes).toString())
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = "SumCost: ${SumOfColumn(notes)}")
            }

            TextField(
                value = noteTitle,
                onValueChange = { noteTitle = it }
            )

            TextField(
                value = noteDescription,
                onValueChange = { noteDescription = it }
            )

            TextField(
                value = noteCost.toString(),
                onValueChange = { noteCost = it.toInt() }
            )

            Button(onClick = {
                viewModel.addNote(note)
                noteTitle = ""
                noteDescription = ""
            }) {
                Text(text = "Add note")
            }

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalItemSpacing = 8.dp,
                content = {
                    items(notes) { n ->
                        NoteCard(
                            n,
                            delete = { viewModel.deleteNote(it) }
                        )
                    }
                })

        }
    }
}

fun SumOfColumn(notes: List<Note>) : Int {
    var sum = 0
    for (item in notes) {
        sum += item.cost
    }
    return sum
}

fun CountRow(notes: List<Note>) : Int {
    var count = 0
    for (item in notes) {
        if (item.title == "at") count += 1
    }
    return count
}