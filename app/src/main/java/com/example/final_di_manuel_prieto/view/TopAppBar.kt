package com.example.final_di_manuel_prieto.view

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@ExperimentalMaterial3Api
@Composable
fun TopAppBar(navController: NavController,nombre:String) {
    TopAppBar(
        title = { Text(text = nombre, color = Color.White) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = {navController.popBackStack()}) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Volver",tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorito", tint = Color.White)
            }
        }
    )
}