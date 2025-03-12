package com.diego.listadetarefas.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diego.listadetarefas.R
import com.diego.listadetarefas.itemlista.TarefaItem
import com.diego.listadetarefas.ui.theme.BLACK
import com.diego.listadetarefas.ui.theme.Purple700
import com.diego.listadetarefas.ui.theme.WHITE
import com.diego.listadetarefas.viewmodel.TarefasViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTarefas(
    navController: NavController,
    viewModel: TarefasViewModel = hiltViewModel()
){
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    val nomeUsuario = viewModel.perfilUsuario().collectAsState(initial = "").value

    //config. alertDialog
    @Composable
    fun dialogDeslogar(onDismiss: () -> Unit, onConfirm: () -> Unit) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text("Sair") },
            text = { Text("Deseja sair do aplicativo?") },
            confirmButton = {
                androidx.compose.material3.TextButton(
                    onClick = {
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate("login")
                    }
                ) {
                    Text("Sim")
                }
            },
            dismissButton = {
                androidx.compose.material3.TextButton(
                    onClick = { onDismiss() }
                ) {
                    Text("Não")
                }
            }
        )
    }




    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple700, // Cor de fundo
                    titleContentColor = Color.White // Cor do título
                ),
                title = {
                    Text(
                        text = "Lista de Tarefas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = WHITE
                    )
                },
                actions = {
                    Text(text = nomeUsuario, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = WHITE)

                    TextButton(
                        onClick = {
                            showDialog = true
                        }
                    ) {
                        Text(text = "Sair", fontSize = 16.sp, color = WHITE)
                    }
                },
                modifier = Modifier.padding(bottom = 30.dp)
            )
        },

        containerColor = BLACK,

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("salvarTarefa")
                },
                shape = RoundedCornerShape(40.dp),
                containerColor = Purple700
            ){
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = "Icone de salvar tarefa"
                )
            }
        }
    ) {paddingValues ->

        //config. alertDialog
        @Composable
        fun dialogDeslogar(onDismiss: () -> Unit, onConfirm: () -> Unit) {
            AlertDialog(
                onDismissRequest = { onDismiss() },
                title = { Text("Sair") },
                text = { Text("Deseja sair do aplicativo?") },
                confirmButton = {
                    androidx.compose.material3.TextButton(
                        onClick = {
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate("login")
                        }
                    ) {
                        Text("Sim")
                    }
                },
                dismissButton = {
                    androidx.compose.material3.TextButton(
                        onClick = { onDismiss() }
                    ) {
                        Text("Não")
                    }
                }
            )
        }


        val listaTarefas = viewModel.recuperarTarefas().collectAsState(mutableListOf()).value

        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues), // Aplica o padding aqui
            verticalArrangement = Arrangement.Top
        ){
             itemsIndexed(listaTarefas){position, _ ->
                 TarefaItem(position = position, listaTarefas = listaTarefas, context = context, navController = navController, viewModel)
             }
        }
    }





    // Agora o Dialog está fora do Card e só aparece quando necessário
    if (showDialog) {
        dialogDeslogar(
            onDismiss = { showDialog = false },
            onConfirm = {
                showDialog = false // Fecha o diálogo
            }
        )
    }
}