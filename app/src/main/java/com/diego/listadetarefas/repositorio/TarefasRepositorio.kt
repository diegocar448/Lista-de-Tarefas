package com.diego.listadetarefas.repositorio

import com.diego.listadetarefas.datasource.DataSource
import com.diego.listadetarefas.model.Tarefa
import kotlinx.coroutines.flow.Flow

class TarefasRepositorio() {

    private val dataSource = DataSource()

    fun salvarTarefa(tarefa: String, descricao: String, prioridade: Int, checkTarefa: Boolean){
        dataSource.salvarTarefa(tarefa, descricao, prioridade, checkTarefa)
    }

    //vai pegar os dados da camada datasource e repassar para o ListaTarefas
    fun recuperarTarefas(): Flow<MutableList<Tarefa>>{
        return dataSource.recuperarTarefas()
    }

    //remover passando tarefa parâmetro
    fun deletarTarefa(tarefa: String){
        dataSource.deletarTarefa(tarefa)
    }

    fun atualizarEstadoTarefa(tarefa: String, checkTarefa: Boolean){
        dataSource.atualizarEstadoTarefa(tarefa, checkTarefa)
    }
}