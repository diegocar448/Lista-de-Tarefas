package com.diego.listadetarefas.viewmodel

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.listadetarefas.model.Tarefa
import com.diego.listadetarefas.repositorio.TarefasRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TarefasViewModel @Inject constructor(private val tarefasRepositorio: TarefasRepositorio): ViewModel() {



    //estado de fluxo que receberá uma tableList de tarefas
    private val _todasTarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    //observar fluxo de dados que vai entrar em todas as tarefas
    private val todasTarefas: StateFlow<MutableList<Tarefa>> = _todasTarefas

    private val _nome = MutableStateFlow("")
    private val nome: StateFlow<String> = _nome


    fun salvarTarefa(tarefa: String, descricao: String, prioridade: Int, checkTarefa: Boolean){
        viewModelScope.launch {
            tarefasRepositorio.salvarTarefa(tarefa, descricao, prioridade, checkTarefa)
        }
    }

    fun recuperarTarefas(): Flow<MutableList<Tarefa>> {
        viewModelScope.launch {
            tarefasRepositorio.recuperarTarefas().collect{
                _todasTarefas.value = it
            }
        }

        return todasTarefas
    }

    fun deletarTarefa(tarefa:String){
        viewModelScope.launch {
            tarefasRepositorio.deletarTarefa(tarefa)
        }
    }

    fun atualizarEstadoTarefa(tarefa: String, checkTarefa: Boolean){
        viewModelScope.launch {
            tarefasRepositorio.atualizarEstadoTarefa(tarefa, checkTarefa)
        }
    }

    fun perfilUsuario(): Flow<String>{
        viewModelScope.launch {
            tarefasRepositorio.perfilUsuario().collect{
                _nome.value = it
            }
        }
        return nome
    }




}