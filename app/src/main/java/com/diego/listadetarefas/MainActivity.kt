package com.diego.listadetarefas

import Cadastro
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diego.listadetarefas.datasource.Auth
import com.diego.listadetarefas.ui.theme.ListaDeTarefasTheme

import com.diego.listadetarefas.view.ListaTarefas
import com.diego.listadetarefas.view.Login
import com.diego.listadetarefas.view.SalvarTarefa
import com.diego.listadetarefas.viewmodel.AuthViewModel
import com.diego.listadetarefas.viewmodel.TarefasViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaDeTarefasTheme {
                val navController = rememberNavController()
                val tarefasViewModel:TarefasViewModel = hiltViewModel()
                val authViewModel: AuthViewModel = hiltViewModel()

                NavHost(navController = navController, startDestination = "login"){
                    composable(
                        route = "login"
                    ){
                        Login(navController)
                    }
                    composable(
                        route = "cadastro"
                    ){
                        Cadastro(navController, authViewModel)
                    }
                    composable(
                        route = "listaTarefas"
                    ){
                        ListaTarefas(navController, tarefasViewModel)
                    }
                    composable(
                        route = "salvarTarefa"
                    ){
                        SalvarTarefa(navController, tarefasViewModel)
                    }
                }
            }
        }
    }
}



