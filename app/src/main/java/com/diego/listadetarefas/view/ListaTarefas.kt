package com.diego.listadetarefas.view

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.diego.listadetarefas.R
import com.diego.listadetarefas.itemlista.TarefaItem
import com.diego.listadetarefas.model.Tarefa
import com.diego.listadetarefas.ui.theme.BLACK
import com.diego.listadetarefas.ui.theme.Purple700
import com.diego.listadetarefas.ui.theme.WHITE

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTarefas(
    navController: NavController
){
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

        val listaTarefas: MutableList<Tarefa> = mutableListOf(
            Tarefa(
                tarefa = "Estudar Kotlin mobile",
                descricao = "asdfasdfasdfasdf",
                prioridade = 0
            ),
            Tarefa(
                tarefa = "Estudar NextJs",
                descricao = "asdfasdfasdfasdf",
                prioridade = 1
            ),
            Tarefa(
                tarefa = "Estudar React",
                descricao = "asdfasdfasdfasdf",
                prioridade = 2
            ),
            Tarefa(
                tarefa = "Estudar Swift IOS",
                descricao = "asdfasdfasdfasdf",
                prioridade = 3
            ),
            Tarefa(
                tarefa = "Jogar um pouquinho",
                descricao = "asdfasdfasdfasdf",
                prioridade = 3
            ),
            Tarefa(
                tarefa = "Estudar Inglês",
                descricao = "asdfasdfasdfasdf",
                prioridade = 3
            )

        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues), // Aplica o padding aqui
            verticalArrangement = Arrangement.Top
        ){
            itemsIndexed(listaTarefas){position, _ ->
                TarefaItem(position, listaTarefas)
            }
        }
    }
}