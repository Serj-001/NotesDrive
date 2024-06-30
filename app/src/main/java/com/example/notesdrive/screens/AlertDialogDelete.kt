package com.example.notesdrive.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.DialogProperties
import com.example.notesdrive.data.Note
import com.example.notesdrive.view.NoteViewModel

@Composable
fun AlertDialogDeleteCard(
    onDismissRequest: () -> Unit,
    onConfirmationRequest: () -> Unit,
    note: Note
) {
    AlertDialog(
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmationRequest()
                }) {
            Text(text = "УДАЛИТЬ")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }) {
            Text(text = "Отмена")
            }
        },
        icon = {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
        },
        title = {
            Text(text = "Внимание!")
        },
        text = {
            Column {
                Text(
                    text = "Удалить текущую заметку?",
                    fontWeight = FontWeight.Bold
                )
                Text(text = note.costType)
                Text(text = note.cost.toString() + " р.")
            }
        }
    )
}