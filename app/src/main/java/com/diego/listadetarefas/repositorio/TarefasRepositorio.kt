package com.diego.listadetarefas.repositorio

import com.diego.listadetarefas.datasource.DataSource

class TarefasRepositorio() {

    private val dataSource = DataSource()

    fun salvarTarefa(tarefa: String, descricao: String, prioridade: Int){
        dataSource.salvarTarefa(tarefa, descricao, prioridade)
    }
}