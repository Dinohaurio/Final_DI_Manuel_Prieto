package com.example.final_di_manuel_prieto.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.final_di_manuel_prieto.viewmodel.LoginScreenViewModel
import com.example.final_di_manuel_prieto.viewmodel.Routes

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Login.route ){
        composable(route = Routes.Login.route){
            loginScreen(navController, LoginScreenViewModel())
        }

        composable(route = Routes.AgregarEquipo.route) {
            AgregarEquipoScreen(navController)
        }

        composable(route = Routes.Mostrar.route){
            MostrarEquipos(navController)
        }

    }
}

