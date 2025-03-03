package com.diego.listadetarefas.datasource

import com.diego.listadetarefas.model.Tarefa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class DataSource {

    //init BD
    private val db = FirebaseFirestore.getInstance()

    //estado de fluxo que receberá uma tableList de tarefas
    private val _todasTarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    //observar fluxo de dados que vai entrar em todas as tarefas
    private val todasTarefas: StateFlow<MutableList<Tarefa>> = _todasTarefas

    fun salvarTarefa(tarefa: String, descricao: String, prioridade: Int){

        val tarefaMap = hashMapOf(
            "tarefa" to tarefa,
            "descricao" to descricao,
            "prioridade" to prioridade
        )

        db.collection("tarefas").document(tarefa).set(tarefaMap).addOnCompleteListener{

        }.addOnFailureListener {

        }
    }

    //lista de tarefas (retorna um fluxo de dados assíncrono) vai retornar para o repositorio TarefasRepositorio que em seguida vai retornar para a view listaTarefas
    fun recuperarTarefas(): Flow<MutableList<Tarefa>> {

        val listaTarefas: MutableList<Tarefa> = mutableListOf()

        db.collection("tarefas").get().addOnCompleteListener{ querySnapshot ->
            if (querySnapshot.isSuccessful) {
                for (documento in querySnapshot.result){
                    val tarefa = documento.toObject(Tarefa::class.java)
                    listaTarefas.add(tarefa)

                    _todasTarefas.value = listaTarefas
                }
            }
        }
        return todasTarefas
    }
}