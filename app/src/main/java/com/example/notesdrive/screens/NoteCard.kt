package com.example.notesdrive.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notesdrive.data.Note
import com.example.notesdrive.navigation.Screen
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun NoteCard (
    note: Note,
    delete: (Note) -> Unit,
    navController: NavHostController
) {
    var isShowAlertDialog by remember {
        mutableStateOf(false)
    }
Box (
    modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(color = MaterialTheme.colorScheme.primaryContainer)
){
    Column {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = note.costType,
                modifier = Modifier.padding(horizontal = 8.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Стоимость: " + note.cost.toString() + " р."
                )
            IconButton(
                onClick = {
                isShowAlertDialog = true },
            ) {
                Icon(Icons.Default.Delete, contentDescription = "delete")
            }
        }

        Text(
            text = note.description,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = ConvertLongToString(note.dateAdded),
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 12.sp
        )
        TextButton(onClick = {
            navController.navigate(route = Screen.UpdateScreen.route)
        }) {
            Text(
                text = "Update",
                color = Color.Red
            )
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
                isShowAlertDialog = false
            },
            note
        )
    }
}

fun ConvertLongToString(timeLong: Long) : String {
    val dateFormatt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val dateLocal = dateFormatt.format(timeLong)
    return dateLocal
}