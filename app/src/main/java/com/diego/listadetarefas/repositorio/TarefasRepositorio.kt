package com.diego.listadetarefas.repositorio

import com.diego.listadetarefas.datasource.DataSource
import com.diego.listadetarefas.model.Tarefa
import kotlinx.coroutines.flow.Flow

class TarefasRepositorio() {

    private val dataSource = DataSource()

    fun salvarTarefa(tarefa: String, descricao: String, prioridade: Int){
        dataSource.salvarTarefa(tarefa, descricao, prioridade)
    }

    //vai pegar os dados da camada datasource e repassar para o ListaTarefas
    fun recuperarTarefas(): Flow<MutableList<Tarefa>>{
        return dataSource.recuperarTarefas()
    }
}