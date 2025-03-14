package com.diego.listadetarefas.view


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diego.listadetarefas.componentes.Botao
import com.diego.listadetarefas.componentes.CaixaDeTexto
import com.diego.listadetarefas.constantes.Constantes
import com.diego.listadetarefas.ui.theme.Purple700
import com.diego.listadetarefas.ui.theme.RADIO_BUTTON_GREEN_DISABLED
import com.diego.listadetarefas.ui.theme.RADIO_BUTTON_GREEN_SELECTED
import com.diego.listadetarefas.ui.theme.RADIO_BUTTON_RED_DISABLED
import com.diego.listadetarefas.ui.theme.RADIO_BUTTON_RED_SELECTED
import com.diego.listadetarefas.ui.theme.RADIO_BUTTON_YELLOW_DISABLED
import com.diego.listadetarefas.ui.theme.RADIO_BUTTON_YELLOW_SELECTED
import com.diego.listadetarefas.ui.theme.WHITE
import com.diego.listadetarefas.viewmodel.TarefasViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarTarefa(
    navController: NavController,
    viemModel: TarefasViewModel = hiltViewModel()
){


    val scope = rememberCoroutineScope()
    val context = LocalContext.current


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

        var tituloTarefa by remember {
            mutableStateOf("")
        }
        var descricaoTarefa by remember {
            mutableStateOf("")
        }
        var semPrioridadeTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeBaixaTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeMediaTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeAltaTarefa by remember {
            mutableStateOf(false)
        }

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

            CaixaDeTexto(
                value = descricaoTarefa,
                onValueChange = { descricaoTarefa = it },
                modifier = Modifier
                    .fillMaxWidth().height(150.dp) // Garante 100% da largura da tela
                    .padding(20.dp, 10.dp, 20.dp, 0.dp), // Adiciona um espaço nas laterais
                label = {
                    Text("Descrição")
                },
                maxLines = 5,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Nível de prioridade")

                RadioButton(
                    selected = prioridadeBaixaTarefa,
                    onClick = {
                        prioridadeBaixaTarefa = !prioridadeBaixaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_GREEN_DISABLED,
                        selectedColor = RADIO_BUTTON_GREEN_SELECTED
                    )
                )

                RadioButton(
                    selected = prioridadeMediaTarefa,
                    onClick = {
                        prioridadeMediaTarefa = !prioridadeMediaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_YELLOW_DISABLED,
                        selectedColor = RADIO_BUTTON_YELLOW_SELECTED
                    )
                )

                RadioButton(
                    selected = prioridadeAltaTarefa,
                    onClick = {
                        prioridadeAltaTarefa = !prioridadeAltaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_RED_DISABLED,
                        selectedColor = RADIO_BUTTON_RED_SELECTED
                    )
                )
            }

            Botao(
                onClick = {

                    var mensagem = true

                    //thread paralela
                    scope.launch(Dispatchers.IO){
                        if(tituloTarefa.isEmpty()){
                            mensagem = false
                        }else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeBaixaTarefa){
                            viemModel.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.PRIORIDADE_BAIXA, checkTarefa = false)
                            mensagem = true
                        }else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeMediaTarefa){
                            viemModel.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.PRIORIDADE_MEDIA, checkTarefa = false)
                            mensagem = true
                        }else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeMediaTarefa){
                            viemModel.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.PRIORIDADE_ALTA, checkTarefa = false)
                            mensagem = true
                        }else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && semPrioridadeTarefa){
                            viemModel.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.SEM_PRIORIDADE, checkTarefa = false)
                            mensagem = true
                        }else if(tituloTarefa.isNotEmpty() && prioridadeBaixaTarefa){
                            viemModel.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.PRIORIDADE_BAIXA, checkTarefa = false)
                            mensagem = true
                        }else if(tituloTarefa.isNotEmpty() && prioridadeMediaTarefa){
                            viemModel.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.PRIORIDADE_MEDIA, checkTarefa = false)
                            mensagem = true
                        }else if(tituloTarefa.isNotEmpty() && prioridadeAltaTarefa){
                            viemModel.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.PRIORIDADE_ALTA, checkTarefa = false)
                            mensagem = true
                        }else{
                            viemModel.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.SEM_PRIORIDADE, checkTarefa = false)
                            mensagem = true                        }
                    }

                    scope.launch(Dispatchers.Main){
                        if(mensagem){
                            Toast.makeText(context, "Sucesso ao salvar a tarefa!", Toast.LENGTH_SHORT).show()
                            //tela anterior
                            navController.popBackStack()
                        }else{
                            Toast.makeText(context, "Titulo é obrigatório!", Toast.LENGTH_SHORT).show()
                        }
                    } 

                },
                modifier = Modifier.fillMaxWidth().height(80.dp).padding(20.dp),
                texto = "Salvar"
            )

        }
    }















}