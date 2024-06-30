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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesdrive.screens.AddNoteScreen
import com.example.notesdrive.screens.MainScreen
import com.example.notesdrive.ui.theme.NotesDriveTheme
import com.example.notesdrive.view.NoteViewModel

class NoteViewModelFactory(val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(application) as T
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val owner = LocalViewModelStoreOwner.current

            owner?.let {
                val viewModel: NoteViewModel = viewModel(
                    it,
                    "NoteViewModel",
                    NoteViewModelFactory(LocalContext.current.applicationContext as Application)
                )
            }
            NotesDriveTheme {
                NavHost(
                    navController = navController,
                    startDestination = "MainScreen"
                ) {
                    composable("MainScreen") {
                        MainScreen(viewModel = viewModel()) {
                            navController.navigate("AddNoteScreen")
                        }
                    }
                    composable("AddNoteScreen") {
                        AddNoteScreen(viewModel = viewModel()) {
                            navController.navigate("MainScreen"){
                                popUpTo("MainScreen"){
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}