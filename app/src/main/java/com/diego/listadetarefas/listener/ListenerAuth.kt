package com.diego.listadetarefas.listener

interface ListenerAuth {
    fun onSucess(mensagem: String)
    fun onFailure(erro: String)
}