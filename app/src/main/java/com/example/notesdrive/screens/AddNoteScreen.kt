package com.example.notesdrive.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.notesdrive.data.Note
import com.example.notesdrive.ui.theme.Purple40
import com.example.notesdrive.ui.theme.Purple80
import com.example.notesdrive.view.NoteViewModel
import java.util.Date

@Composable
fun AddNoteScreen(viewModel: NoteViewModel, onClick: () -> Unit) {

    val notes by viewModel.loadAllByDateAdded.observeAsState(initial = listOf())

    var noteDescription by remember {
        mutableStateOf("")
    }
    var noteCost by remember {
        mutableIntStateOf(0)
    }
    var noteCostType by remember {
        mutableStateOf("")
    }

    val note = Note(
        description = noteDescription,
        dateAdded = Date().time,
        cost = noteCost,
        costType = noteCostType
    )

    Scaffold(
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            noteCostType = CostTypeDropMenu()

            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp),
                value = noteDescription,
                onValueChange = { noteDescription = it },
                placeholder = { Text(text = "Описание") },
                shape = RoundedCornerShape(16.dp)

            )

            OutlinedTextField(
                value = noteCost.toString(),
                onValueChange = { noteCost = it.toInt() },
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