package com.diego.listadetarefas.itemlista

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diego.listadetarefas.ui.theme.WHITE

@Composable
fun TarefaItem(){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = WHITE // Define a cor de fundo do Card
        )
    ) {  }
}

@Composable
@Preview
private fun TarefaItemPreview(){
    TarefaItem()
}