package com.example.notesdrive

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesdrive.data.Note
import com.example.notesdrive.navigation.SetupNavGraph
import com.example.notesdrive.screens.AddNoteScreen
import com.example.notesdrive.screens.MainScreen
import com.example.notesdrive.ui.theme.NotesDriveTheme
import com.example.notesdrive.view.NoteViewModel
import java.util.Date

class NoteViewModelFactory(val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(application) as T
    }
}

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val owner = LocalViewModelStoreOwner.current

            owner?.let {
                val viewModel: NoteViewModel = viewModel(
                    it,
                    "NoteViewModel",
                    NoteViewModelFactory(LocalContext.current.applicationContext as Application)
                )
            }
            val viewModel: NoteViewModel = viewModel()
            NotesDriveTheme {
                val note = Note(
                    description = viewModel.noteDescription,
                    dateAdded = Date().time,
                    cost = viewModel.noteCost,
                    costType = viewModel.noteCostType
                )
                navController = rememberNavController()
                SetupNavGraph(navController = navController, note)
            }
        }
    }
}