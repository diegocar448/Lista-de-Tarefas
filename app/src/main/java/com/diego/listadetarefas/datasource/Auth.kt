package com.diego.listadetarefas.datasource

import com.diego.listadetarefas.listener.ListenerAuth
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class Auth @Inject constructor() {
    val auth  = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    fun cadastro(nome:String, email: String, senha: String, listenerAuth: ListenerAuth){
        if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
            listenerAuth.onFailure("Preencha todos os campos!")
        }else{
            auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener { cadastro ->
                    if (cadastro.isSuccessful) {
                        // Identificador único gerado pelo Auth do Firebase
                        val usuarioID = auth.currentUser?.uid.toString()

                        val dadosUsuarioMap = hashMapOf(
                            "nome" to nome,
                            "email" to email,
                            "usuarioID" to usuarioID
                        )

                        db.collection("usuarios").document(usuarioID)
                            .set(dadosUsuarioMap)
                            .addOnCompleteListener {
                                listenerAuth.onSucess("Sucesso ao cadastrar o usuário!", "login")
                            }
                            .addOnFailureListener { exception ->
                                listenerAuth.onFailure("Erro ao salvar usuário no banco: ${exception.message}")
                            }
                    } else {
                        listenerAuth.onFailure("Falha ao cadastrar o usuário. Tente novamente.")
                    }
                }
                .addOnFailureListener { exception ->
                    //tipos de validacoes disponiveis
                    val erro = when (exception) {
                        is FirebaseAuthUserCollisionException -> "Essa conta já foi cadastrada!"
                        is FirebaseAuthWeakPasswordException -> "Digite uma senha com no mínimo 6 caracteres!"
                        is FirebaseNetworkException -> "Sem conexão com a internet!"
                        else -> "Erro ao criar conta: ${exception.message}"
                    }
                    listenerAuth.onFailure(erro)
                }
        }
    }
    fun login(email: String, senha: String, listenerAuth: ListenerAuth){
        if (email.isEmpty() || senha.isEmpty()) {
            listenerAuth.onFailure("Preencher todo os campos!")
        }else{
            auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener{ autenticacao ->
                if (autenticacao.isSuccessful) {
                    listenerAuth.onSucess("Sucesso ao fazer o login!", "listaTarefas")
                }
            }.addOnFailureListener{ exception ->
                val erro = when(exception){
                    is FirebaseAuthInvalidCredentialsException -> "A senha esta errada!"
                    is FirebaseNetworkException -> "Sem conexao com a internet!"
                    else -> "E-mail inválido!"
                }

                listenerAuth.onFailure(erro)
            }
        }
    }
}