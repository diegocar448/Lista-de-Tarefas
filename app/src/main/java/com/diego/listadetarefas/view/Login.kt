package com.diego.listadetarefas.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diego.listadetarefas.R
import com.diego.listadetarefas.R.drawable
import com.diego.listadetarefas.componentes.BotaoAuth
import com.diego.listadetarefas.listener.ListenerAuth
import com.diego.listadetarefas.ui.theme.DARK_BLUE
import com.diego.listadetarefas.ui.theme.DARK_PINK
import com.diego.listadetarefas.ui.theme.LIGHT_BLUE
import com.diego.listadetarefas.ui.theme.Purple700
import com.diego.listadetarefas.ui.theme.ShapeEditText
import com.diego.listadetarefas.ui.theme.WHITE
import com.diego.listadetarefas.viewmodel.AuthViewModel
import com.diego.listadetarefas.viewmodel.TarefasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Login(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
){

    val context = LocalContext.current

    var usuarioLogado = viewModel.verificarUsuarioLogado().collectAsState(initial = false).value


    LaunchedEffect(usuarioLogado) {
        if (usuarioLogado){
            navController.navigate("listaTarefas")
        }else{
            usuarioLogado = false
        }
    }


    Scaffold(
        modifier = Modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(
                    DARK_PINK,
                    DARK_BLUE
                )
            )
        ),
        //mudar o fundo de branco padrao para duas cores degrade
        containerColor = Color.Transparent

    ) { //paddingValues ->

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            //pegar toda largura e altura
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){

            var email by remember {
                mutableStateOf("")
            }

            var senha by remember {
                mutableStateOf("")
            }

            var visibilidadeSenha by remember {
                mutableStateOf(true)
            }

            var mensagem by remember {
                mutableStateOf("")
            }



            
            var icon = if (visibilidadeSenha) {
                painterResource(id = drawable.baseline_visibility)
            }else{
                painterResource(id = drawable.baseline_visibility_off)
            }

            Icon(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = drawable.baseline_task),
                contentDescription = "Icone de tarefas",
                tint = WHITE
            )

            OutlinedTextField(
                value = email ,
                onValueChange = {
                    email = it
                },
                label = {
                    Text(text = "E-mail", color = Purple700, modifier = Modifier.background(WHITE)) // Cor de fundo)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 29.dp, 0.dp),
                colors = TextFieldDefaults.colors(
                    cursorColor = Purple700,
                    focusedContainerColor = WHITE, // Cor do fundo quando focado
                    unfocusedContainerColor = WHITE, // Cor do fundo quando não focado
                    disabledContainerColor = LIGHT_BLUE, // Cor do fundo quando desabilitado
                    focusedIndicatorColor = Purple700
                ),
                shape = ShapeEditText.small,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                //Icone a direita
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_email),
                        contentDescription = "Icone de email"
                    )
                },
                maxLines = 1
                //Icone a esquerda
                //leadingIcon =""
            )

            OutlinedTextField(
                value = senha ,
                onValueChange = {
                    senha = it
                },
                label = {
                    Text(text = "Senha", color = Purple700, modifier = Modifier.background(WHITE)) // Cor de fundo)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 29.dp, 0.dp),
                colors = TextFieldDefaults.colors(
                    cursorColor = Purple700,
                    focusedContainerColor = WHITE, // Cor do fundo quando focado
                    unfocusedContainerColor = WHITE, // Cor do fundo quando não focado
                    disabledContainerColor = LIGHT_BLUE, // Cor do fundo quando desabilitado
                    focusedIndicatorColor = Purple700
                ),
                shape = ShapeEditText.small,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                //Icone a direita
                trailingIcon = {
                    IconButton(
                        onClick = {
                            visibilidadeSenha = !visibilidadeSenha
                        }
                    ) {
                        Icon(
                            painter = icon,
                            contentDescription = "Icone da senha"
                        )
                    }

                },
                maxLines = 1,
                //Icone a esquerda
                //leadingIcon =""
                //se for verdades não faz nenhuma alteração
                visualTransformation = if (visibilidadeSenha) VisualTransformation.None
                    else PasswordVisualTransformation()
            )

            Text(text = mensagem, fontSize = 18.sp, fontWeight = FontWeight.Medium, color = WHITE)

            BotaoAuth(
                onClick = {
                    viewModel.login(email, senha, object: ListenerAuth{
                        override fun onSucess(mensagem: String, tela: String) {
                            Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
                            navController.navigate(tela)
                        }

                        override fun onFailure(erro: String) {
                            mensagem = erro
                        }

                    })
                },
                text = "Entrar"
            )
            Spacer(modifier = Modifier.padding(20.dp))

            TextButton(
                onClick = {
                    navController.navigate("cadastro")
                }
            ) {
                Text(
                    text = "Não tem conta? cadastre-se agora!",
                    color = WHITE,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp))

        }
    }

}