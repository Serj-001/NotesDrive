package com.example.notesdrive.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties

@Composable
fun AlertDialogDeleteCard(
    onDismissRequest: () -> Unit,
    onConfirmationRequest: () -> Unit
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
            Text(text = "DELETE")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }) {
            Text(text = "Cancel")
            }
        },
        icon = {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "dalete")
        },
        title = {
            Text(text = "Warning!")
        },
        text = {
            Text(text = "Delete the current note?")
        }
    )
}