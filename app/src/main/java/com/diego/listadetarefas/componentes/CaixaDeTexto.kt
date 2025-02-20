package com.diego.listadetarefas.componentes


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diego.listadetarefas.ui.theme.BLACK
import com.diego.listadetarefas.ui.theme.LIGHT_BLUE
import com.diego.listadetarefas.ui.theme.WHITE




@Composable
fun CaixaDeTexto(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange,
        modifier = Modifier,
        label = {
            Text(text = label)
        },
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = WHITE, // Fundo transparente quando focado
            //unfocusedContainerColor = Color.Transparent, // Fundo transparente quando não focado
            //focusedBorderColor
            focusedIndicatorColor = LIGHT_BLUE, // Cor da borda quando focado
            //unfocusedIndicatorColor = Color.Gray, // Cor da borda quando não focado
            cursorColor = LIGHT_BLUE, // Cor do cursor
            focusedLabelColor = LIGHT_BLUE, // Cor do rótulo quando focado
            //unfocusedLabelColor = Color.Gray, // Cor do rótulo quando não focado
            //textColor
            focusedTextColor = BLACK, // Cor do texto quando focado equivale ao textColor
            //unfocusedTextColor = Color.DarkGray // Cor do texto quando não focado
        )
    )

}

@Composable
@Preview
private fun CaixaDeTextoPreview(){
    CaixaDeTexto(
        value = "Texto de exemplo",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        label = "Descrição"
    )
}
