package com.example.notesdrive.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.notesdrive.view.NoteViewModel

@Composable
fun UpdateNoteScreen(
    viewModel: NoteViewModel
) {
    Scaffold { innertPadding ->

        Column(
            modifier = Modifier.padding(innertPadding)
        ) {
            Text(text = "UpdateNoteScreen")
            Button(onClick = {  }) {
                Text(text = "Update")
            }
        }
    }
}