package com.example.final_di_manuel_prieto.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.final_di_manuel_prieto.model.Equipo
import com.example.final_di_manuel_prieto.viewmodel.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarEquipoScreen(navController: NavController){
    var nombre by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var deporte by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    val CustomTeal = Color(0xFFA6FF80)     
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomTeal),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "AGREGAR EQUIPO NUEVO", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(16.dp).padding(16.dp))
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del equipo") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = ciudad,
            onValueChange = { ciudad = it },
            label = { Text("Ciudad") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = deporte,
            onValueChange = { deporte = it },
            label = { Text("Deporte") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val nuevoEquipo = Equipo(
                    id = UUID.randomUUID().toString(),
                    nombre = nombre,
                    ciudad = ciudad,
                    deporte = deporte,
                    descripcion = descripcion
                )
                CoroutineScope(Dispatchers.IO).launch {
                    agregarEquipo(nuevoEquipo)
                }
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .align(Alignment.End).padding(16.dp)
        ) {
            Text("Guardar")
        }
    }
}