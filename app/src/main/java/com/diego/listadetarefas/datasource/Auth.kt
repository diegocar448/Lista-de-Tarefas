package com.diego.listadetarefas.datasource

import com.diego.listadetarefas.listener.ListenerAuth
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class Auth @Inject constructor() {
    val auth  = FirebaseAuth.getInstance()

    fun cadastro(email: String, senha: String, listenerAuth: ListenerAuth){
        if(email.isEmpty() || senha.isEmpty()){
            listenerAuth.onFailure("Preencha todos os campos!")
        }else{
            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener{ cadastro ->
                if(cadastro.isSuccessful){
                    listenerAuth.onSucess("Sucesso ao cadastrar o usuario!")
                }
            }
        }
    }
}