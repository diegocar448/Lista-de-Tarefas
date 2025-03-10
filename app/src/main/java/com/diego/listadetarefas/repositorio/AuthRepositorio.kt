package com.diego.listadetarefas.repositorio

import com.diego.listadetarefas.datasource.Auth
import com.diego.listadetarefas.listener.ListenerAuth
import javax.inject.Inject

class AuthRepositorio @Inject constructor(private val auth: Auth) {

    fun cadastro(email:String, senha:String, listenerAuth: ListenerAuth){
        auth.cadastro(email, senha, listenerAuth)
    }
}