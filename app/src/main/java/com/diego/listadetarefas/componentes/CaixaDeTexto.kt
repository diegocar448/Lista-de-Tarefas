package com.diego.listadetarefas.componentes

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import java.lang.reflect.Modifier


@Composable
fun CaixaDeTexto(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = androidx.compose.ui.Modifier,
        label = {
            Text(text = label)
        },
        maxLines = 1

    )

}

@Composable
@Preview
private fun CaixaDeTextoPreview(){
//    CaixaDeTexto(
//        value = "Texto de exemplo",
//        onValueChange = {},
//        label = (String) -> Unit
//    )
}
