package com.diego.listadetarefas.itemlista

import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.diego.listadetarefas.R
import com.diego.listadetarefas.model.Tarefa
import com.diego.listadetarefas.ui.theme.RADIO_BUTTON_GREEN_SELECTED
import com.diego.listadetarefas.ui.theme.RADIO_BUTTON_RED_SELECTED
import com.diego.listadetarefas.ui.theme.RADIO_BUTTON_YELLOW_SELECTED
import com.diego.listadetarefas.ui.theme.ShapeCardPrioridade
import com.diego.listadetarefas.ui.theme.WHITE

@Composable
fun TarefaItem(
    position: Int,
    listaTarefa: MutableList<Tarefa>
){

    val tituloTarefa = listaTarefa[position].tarefa
    val descricaoTarefa = listaTarefa[position].descricao
    val prioridade = listaTarefa[position].prioridade

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
                onClick = {},
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
}

//@Composable
//@Preview
//private fun TarefaItemPreview(){
//    TarefaItem()
//}