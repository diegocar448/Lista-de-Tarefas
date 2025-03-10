package com.diego.listadetarefas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.listadetarefas.listener.ListenerAuth
import com.diego.listadetarefas.repositorio.AuthRepositorio
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authRepositorio: AuthRepositorio): ViewModel() {
    fun cadastro(email: String, senha:String, listenerAuth: ListenerAuth){
        viewModelScope.launch{
            authRepositorio.cadastro(email, senha, listenerAuth)
        }
    }
}