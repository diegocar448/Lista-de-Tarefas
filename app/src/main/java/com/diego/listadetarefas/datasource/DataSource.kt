package com.diego.listadetarefas.datasource

import com.diego.listadetarefas.model.Tarefa
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class DataSource @Inject constructor(){

    //init BD
    private val db = FirebaseFirestore.getInstance()

    //estado de fluxo que receberá uma tableList de tarefas
    private val _todasTarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    //observar fluxo de dados que vai entrar em todas as tarefas
    private val todasTarefas: StateFlow<MutableList<Tarefa>> = _todasTarefas

    private val _nome = MutableStateFlow("")
    private val nome: StateFlow<String> = _nome

    fun salvarTarefa(tarefa: String, descricao: String, prioridade: Int, checkTarefa: Boolean){

        val usuarioID = FirebaseAuth.getInstance().currentUser?.uid.toString()

        val tarefaMap = hashMapOf(
            "tarefa" to tarefa,
            "descricao" to descricao,
            "prioridade" to prioridade,
            "checkTarefa" to checkTarefa
        )

        db.collection("tarefas")
            .document(usuarioID)
            //esse collection pegara uma sub colecao
            .collection("tarefas_usuario")
            .document(tarefa)
            .set(tarefaMap).addOnCompleteListener{

        }.addOnFailureListener {

        }
    }

    //lista de tarefas (retorna um fluxo de dados assíncrono) vai retornar para o repositorio TarefasRepositorio que em seguida vai retornar para a view listaTarefas
    fun recuperarTarefas(): Flow<MutableList<Tarefa>> {

        val listaTarefas: MutableList<Tarefa> = mutableListOf()
        val usuarioID = FirebaseAuth.getInstance().currentUser?.uid.toString()


        db.collection("tarefas")
            .document(usuarioID)
            .collection("tarefas_usuario")
            .get()
            .addOnCompleteListener{ querySnapshot ->
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


    //deletar pegando pelo parâmetro tarefa do firestore
    fun deletarTarefa(tarefa:String){
        db.collection("tarefas").document(tarefa).delete().addOnCompleteListener{

        }.addOnFailureListener{

        }
    }

    fun atualizarEstadoTarefa(tarefa: String, checkTarefa:Boolean){
        db.collection("tarefas").document(tarefa).update("checkTarefa", checkTarefa).addOnCompleteListener{

        }.addOnFailureListener{

        }
    }

    fun perfilUsuario(): Flow<String>{
        //pegar o uid do usuário logado
        val usuarioID = FirebaseAuth.getInstance().currentUser?.uid.toString()

        db.collection("usuarios").document(usuarioID).get().addOnCompleteListener{ documentSnapshot ->
            if (documentSnapshot.isSuccessful){
                val nome = documentSnapshot.result.getString("nome").toString()

                _nome.value = nome
            }
        }
        return nome
    }
}