package com.example.final_di_manuel_prieto.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val loading = MutableLiveData(false)

    fun signInWithEmailAndPassword(email:String,password: String,home: ()->Unit)
    = viewModelScope.launch {
        try{
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{ task->
                    if(task.isSuccessful){
                        Log.d("Deportes","signInWithEmailAndPassword logueado")
                        home()
                    }else{
                        if(task.isCanceled){
                            Log.d("Deportes","signInWithEmailAndPassword: ${task.result}")
                        }
                    }
                }
        } catch (ex:Exception){
            Log.d("Deportes","signInWithEmailAndPassword: ${ex.message}")

        }
    }

    fun createUserWithEmailAndPassword(
        email:String,
        password:String,
        home: ()-> Unit
    ){
        if(loading.value == false){
            loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        home()
                    }else{
                        if(task.isCanceled){
                            Log.d("Deportes","createUserWithEmailAndPassword: ${task.result}")
                        }
                    }
                    loading.value = false
                }
        }
    }
}