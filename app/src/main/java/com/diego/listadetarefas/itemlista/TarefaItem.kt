package com.diego.listadetarefas.itemlista

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.diego.listadetarefas.R
import com.diego.listadetarefas.model.Tarefa
import com.diego.listadetarefas.repositorio.TarefasRepositorio
import com.diego.listadetarefas.ui.theme.RADIO_BUTTON_GREEN_SELECTED
import com.diego.listadetarefas.ui.theme.RADIO_BUTTON_RED_SELECTED
import com.diego.listadetarefas.ui.theme.RADIO_BUTTON_YELLOW_SELECTED
import com.diego.listadetarefas.ui.theme.ShapeCardPrioridade
import com.diego.listadetarefas.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarefaItem(
    position: Int,
    listaTarefas: MutableList<Tarefa>,
    context: Context,
    navController: NavController
){

    var showDialog by remember { mutableStateOf(false) }

    val tituloTarefa = listaTarefas[position].tarefa
    val descricaoTarefa = listaTarefas[position].descricao
    val prioridade = listaTarefas[position].prioridade
    val tarefas = listaTarefas[position].prioridade

    val scope = rememberCoroutineScope()
    val tarefasRepositorio = TarefasRepositorio()


    //config. alertDialog
    @Composable
    fun dialogDeletar(onDismiss: () -> Unit, onConfirm: () -> Unit) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text("Deletar Tarefa") },
            text = { Text("Deseja excluir a tarefa?") },
            confirmButton = {
                androidx.compose.material3.TextButton(
                    onClick = {
                        tarefasRepositorio.deletarTarefa(tituloTarefa.toString())

                        scope.launch(Dispatchers.Main){
                            listaTarefas.removeAt(position)
                            navController.navigate("listaTarefas")
                            Toast.makeText(context, "Sucesso ao deletar tarefa!", Toast.LENGTH_SHORT).show()
                        }
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

    var nivelDePrioridade: String = when(prioridade){
        0 -> {
            "Sem prioridade"
        }
        1 -> {
            "Prioridade Baixa"
        }
        2 -> {
            "Prioridade Média"
        }
        else -> {
            "Prioridade Alta"
        }
    }
    val color = when(prioridade){
        0 -> {
            Color.Black
        }
        1 -> {
            RADIO_BUTTON_GREEN_SELECTED
        }
        2 -> {
            RADIO_BUTTON_YELLOW_SELECTED
        }
        else -> {
            RADIO_BUTTON_RED_SELECTED
        }
    }



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = WHITE // Define a cor de fundo do Card
        )
    ) {
        //construir item de lista
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {
            val (txtTitulo, txtDescricao, cardPrioridade, txtPrioridade, btDeletar) = createRefs()

            Text(
                text = tituloTarefa.toString(),
                modifier = Modifier.constrainAs(txtTitulo){
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = descricaoTarefa.toString(),
                modifier = Modifier.constrainAs(txtDescricao){
                    top.linkTo(txtTitulo.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = nivelDePrioridade,
                modifier = Modifier.constrainAs(txtPrioridade){
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = color,
                ),
                modifier = Modifier.width(30.dp).height(30.dp).constrainAs(cardPrioridade){
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(txtPrioridade.end, margin = 50.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                },
                shape = ShapeCardPrioridade.large
            ) {
            }

            IconButton(
                onClick = {
                    // Exibir o Dialog quando `showDialog` for true
                    showDialog = true
                },
                modifier = Modifier.constrainAs(btDeletar){
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(cardPrioridade.end, margin = 90.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                )
            }


        }
    }

    // 🔥 Agora o Dialog está fora do Card e só aparece quando necessário
    if (showDialog) {
        dialogDeletar(
            onDismiss = { showDialog = false },
            onConfirm = {
                showDialog = false // Fecha o diálogo
            }
        )
    }
}

//@Composable
//@Preview
//private fun TarefaItemPreview(){
//    TarefaItem()
//}