package com.example.final_di_manuel_prieto.view

import android.util.Log
import com.example.final_di_manuel_prieto.model.Equipo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


val db = FirebaseFirestore.getInstance()
//Obtener Equipos
suspend fun getEquipos(tabla: String): List<Equipo> {
    val equipos = mutableListOf<Equipo>()

    try {
        val querySnapshot = db.collection(tabla).get().await()

        for (document in querySnapshot.documents) {
            val id = document.getString("id") ?: ""
            val nombre = document.getString("nombre") ?: ""
            val ciudad = document.getString("ciudad") ?: ""
            val deporte = document.getString("deporte") ?: ""
            val descripcion = document.getString("descripcion") ?: ""

            equipos.add(Equipo(id, nombre, ciudad, deporte, descripcion))
        }
    } catch (e: Exception) {
        Log.e("TAG", "Error al obtener los equipos", e)
    }

    return equipos
}

//Agregar Equipos
suspend fun agregarEquipo(nuevoEquipo: Equipo) {
    try {
        db.collection("equipo").add(nuevoEquipo)
            .await()
    } catch (e: Exception) {
        Log.e("TAG", "Error al agregar el equipo", e)
    }
}



