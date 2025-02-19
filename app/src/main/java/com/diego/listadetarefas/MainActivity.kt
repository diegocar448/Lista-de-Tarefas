package com.diego.listadetarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diego.listadetarefas.ui.theme.ListaDeTarefasTheme
import com.diego.listadetarefas.view.ListaTarefas
import com.diego.listadetarefas.view.SalvarTarefa

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaDeTarefasTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "listaTarefas"){
                    composable(
                        route = "listaTarefas"
                    ){
                        ListaTarefas(navController)
                    }
                    composable(
                        route = "salvarTarefa"
                    ){
                        SalvarTarefa(navController)
                    }
                }
            }
        }
    }
}



