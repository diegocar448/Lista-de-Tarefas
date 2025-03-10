package com.diego.listadetarefas.datasource

import com.diego.listadetarefas.listener.ListenerAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class Auth @Inject constructor() {
    val auth  = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    fun cadastro(nome:String, email: String, senha: String, listenerAuth: ListenerAuth){
        if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
            listenerAuth.onFailure("Preencha todos os campos!")
        }else{
            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener{ cadastro ->
                if(cadastro.isSuccessful){

                    //identificador unico gerado pelo Auth do firebase
                    var usuarioID = FirebaseAuth.getInstance().currentUser?.uid.toString()

                    val dadosUsuarioMap = hashMapOf(
                        "nome" to nome,
                        "email" to email,
                        "usuarioID" to usuarioID
                    )

                    db.collection("usuarios").document(usuarioID).set(dadosUsuarioMap).addOnCompleteListener{
                        listenerAuth.onSucess("Sucesso ao cadastrar o usuario!", "login")
                    }.addOnFailureListener{
                        listenerAuth.onFailure("Erro inesperado")
                    }

                }
            }
        }
    }
}