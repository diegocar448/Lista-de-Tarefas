package com.diego.listadetarefas.componentes



import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diego.listadetarefas.ui.theme.LIGHT_BLUE
import com.diego.listadetarefas.ui.theme.Purple700
import com.diego.listadetarefas.ui.theme.ShapeEditText
import com.diego.listadetarefas.ui.theme.WHITE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaixaDeTexto(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier,
    maxLines: Int
){
    OutlinedTextField(
        value = value,
        onValueChange,
        label = label,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            cursorColor = Purple700,
            focusedIndicatorColor = Purple700, // Borda quando focado
            unfocusedIndicatorColor = LIGHT_BLUE, // Borda quando não focado
            disabledIndicatorColor = WHITE, // Borda quando desabilitado
            focusedContainerColor = WHITE, // Cor do fundo quando focado
            unfocusedContainerColor = WHITE, // Cor do fundo quando não focado
            disabledContainerColor = LIGHT_BLUE // Cor do fundo quando desabilitado
        ),
        modifier = modifier,
        maxLines = maxLines,
        shape = RoundedCornerShape(10.dp)
    )


}