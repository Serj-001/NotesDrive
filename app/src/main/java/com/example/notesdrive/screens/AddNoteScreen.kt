package com.example.notesdrive.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.notesdrive.data.Note
import com.example.notesdrive.view.NoteViewModel
import java.util.Date

@Composable
fun AddNoteScreen(viewModel: NoteViewModel, onClick: () -> Unit) {

    //val notes by viewModel.loadAllByDateAdded.observeAsState(initial = listOf())

    var noteCostType by remember {
        mutableStateOf("")
    }

    val note = Note(
        description = viewModel.noteDescription,
        dateAdded = Date().time,
        cost = viewModel.noteCost.toIntOrNull(),
        costType = noteCostType
    )

    Scaffold(
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            noteCostType = CostTypeDropMenu()

            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp),
                value = viewModel.noteDescription,
                onValueChange = { viewModel.changeDescription(it) },
                placeholder = { Text(text = "Описание") },
                shape = RoundedCornerShape(16.dp)

            )

            OutlinedTextField(
                value = viewModel.noteCost,
                onValueChange = { viewModel.changeCost(it).toString() },
                placeholder = { Text(text = "Стоимость") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Button(onClick = {
                viewModel.addNote(note)
                onClick()
            }) {
                Text(text = "Добавить запись")
            }
        }
    }
}