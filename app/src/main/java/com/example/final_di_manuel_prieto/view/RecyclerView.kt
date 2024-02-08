package com.example.final_di_manuel_prieto.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.final_di_manuel_prieto.R
import com.example.final_di_manuel_prieto.R.drawable.summer
import com.example.final_di_manuel_prieto.viewmodel.Routes
import com.example.final_di_manuel_prieto.model.Equipo

@Composable
fun RecyclerEquipos(list: List<Equipo>, navController: NavController, modifier: Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp), modifier = Modifier.fillMaxSize()
    ) {
        items(list) { equipo ->
            CardEquipo(equipo = equipo, navController = navController, modifier = Modifier.background(Color.Black))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardEquipo(equipo: Equipo, navController: NavController, modifier: Modifier) {
    var show by remember {
        mutableStateOf(false)
    }
    val CustomTeal = Color(0xFF17FCE6)
    val Custom = Color(0xFF000000)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Custom, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            show = true
        }
    ) {
        Row(
            modifier = Modifier
                .background(CustomTeal)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.escudo),
                contentDescription = null,
                modifier = Modifier
                    .size(75.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                modifier = Modifier
                    .background(CustomTeal)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = equipo.nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Custom
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Ciudad:, ${equipo.ciudad}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Custom
                )
                Text(
                    text = "Deporte: ${equipo.deporte}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Custom
                )
                Text(
                    text = "Descripción: ${equipo.descripcion}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Custom
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    EquipoAlertDialog(
        show,
        equipo.nombre,
        equipo.ciudad,
        equipo.deporte,
        equipo.descripcion,
        onDismiss = { show = false },
        onConfirm = {}
    )
}

@Composable
fun EquipoAlertDialog(
    show: Boolean,
    equipoNombre: String,
    equipoCiudad: String,
    equipoDeporte: String,
    equipoDescripcion: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            containerColor = Color.Yellow,
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Detalles del Equipo") },
            text = {
                Column {
                    Text(text = "$equipoNombre", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Ciudad: $equipoCiudad", fontSize = 18.sp)
                    Text(text = "Deporte: $equipoDeporte", fontSize = 18.sp)
                    Text(text = "Descripción: $equipoDescripcion", fontSize = 18.sp)
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Atrás")
                }
            }
        )
    }
}
