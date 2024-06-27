package com.example.notesdrive.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notesdrive.data.Note
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.random.Random

@Composable
fun NoteCard (
    note: Note,
    delete: (Note) -> Unit
) {
    var isShowAlertDialog by remember {
        mutableStateOf(false)
    }
Box (
    modifier = Modifier
        .clip(RoundedCornerShape(16.dp))
        .background(Color(Random.nextLong(0xFFFFFFFF)))

){
    Column {
        Text(text = note.title)
        Text(text = note.description)
        Row {
            Text(text = ConvertLongToString(note.dateAdded))
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = note.cost.toString())
        }

        IconButton(onClick = {
            isShowAlertDialog = true
        }) {
            Icon(Icons.Default.Delete, contentDescription = "delete")
        }
    }

}

    if (isShowAlertDialog) {
        AlertDialogDeleteCard(
            onDismissRequest = {
                isShowAlertDialog = false
            },
            onConfirmationRequest = {
                delete(note)
            }
        )
    }
}

fun ConvertLongToString(timeLong: Long) : String {
    val dateFormatt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val dateLocal = dateFormatt.format(timeLong)
    return dateLocal
}