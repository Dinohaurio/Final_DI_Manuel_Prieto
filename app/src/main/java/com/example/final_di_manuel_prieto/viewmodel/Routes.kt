package com.example.final_di_manuel_prieto.viewmodel

sealed class Routes(val route:String){
    object Login : Routes("login_screen")

    object Mostrar : Routes("mostrar_screen/{parametro}") {
        fun createRoute(parametro: String) = "mostrar_screen/$parametro"
    }
    object AgregarEquipo : Routes("agregar_equipo_screen")
}
