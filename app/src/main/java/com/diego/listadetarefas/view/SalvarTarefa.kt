package com.diego.listadetarefas.view


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.diego.listadetarefas.componentes.CaixaDeTexto
import com.diego.listadetarefas.ui.theme.Purple700
import com.diego.listadetarefas.ui.theme.WHITE

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarTarefa(
    navController: NavController
){

    var tituloTarefa by remember {
        mutableStateOf("")
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Salvar Tarefa ",
                        fontSize = 18.sp,
                        color = WHITE,
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(Purple700)
            )
        }
    ){ paddingValues -> // Recebe o padding fornecido pelo Scaffold
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues) // Aplica o padding aqui
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top


        ) {

            CaixaDeTexto(
                value = tituloTarefa,
                onValueChange = { tituloTarefa = it },
                modifier = Modifier
                    .fillMaxWidth() // Garante 100% da largura da tela
                    .padding(20.dp, 20.dp, 20.dp, 0.dp), // Adiciona um espaço nas laterais
                label = {
                    Text("Título Tarefa")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
        }
    }















}