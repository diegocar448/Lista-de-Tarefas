package com.diego.listadetarefas.repositorio

import com.diego.listadetarefas.datasource.Auth
import com.diego.listadetarefas.listener.ListenerAuth
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class AuthRepositorio @Inject constructor(private val auth: Auth) {

    fun cadastro(nome: String, email:String, senha:String, listenerAuth: ListenerAuth){
        auth.cadastro(nome, email, senha, listenerAuth)
    }

    fun login(email:String, senha:String, listenerAuth: ListenerAuth){
        auth.login(email, senha, listenerAuth)
    }

    fun verificarUsuarioLogado(): Flow<Boolean> {
        return auth.verificarUsuarioLogado()
    }
}