package com.example.final_di_manuel_prieto.view

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Start
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.final_di_manuel_prieto.R
import com.example.final_di_manuel_prieto.viewmodel.LoginScreenViewModel
import com.example.final_di_manuel_prieto.viewmodel.Routes

@Composable
fun loginScreen(navController: NavController, viewModel: LoginScreenViewModel){
    val showLoginForm = rememberSaveable{
        mutableStateOf(true)
    }
    val CustomTeal = Color(0xFFA6FF80)
    val context = LocalContext.current
    val painter: Painter = painterResource(id = R.drawable.deportes)
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(CustomTeal)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .background(CustomTeal)
            ){
            Spacer(modifier= Modifier.height(35.dp))
                Image(
                    painter = painter,
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp),
                    contentDescription = "Descripicion de la imagen"
                )
                Spacer(modifier= Modifier.height(50.dp).align(Alignment.Start))
                if(showLoginForm.value){
                    Text(text="Iniciar sesión", Modifier.padding(16.dp).align(Alignment.Start), fontSize = 20.sp)
                    UserForm(
                        isCreateAccount = false
                    ){
                        email, password ->
                        Log.d("DeportesLogin","Se ha registrado con $email y $password")
                        viewModel.signInWithEmailAndPassword(email,password){
                            navController.navigate(route= Routes.Mostrar.route)
                            Toast.makeText(context, "Se Inicio Sesion Correctamente", Toast.LENGTH_SHORT).show() //Temporal
                        }
                    }
                }else{
                    Text(text="Crea una cuenta",Modifier.padding(16.dp).align(Alignment.Start), fontSize = 20.sp)
                    UserForm(
                        isCreateAccount = true
                    ){
                            email, password ->
                        Log.d("DeportesLogin","Creando cuenta con $email y $password")
                        viewModel.createUserWithEmailAndPassword(email,password){
                            navController.navigate(route= Routes.Mostrar.route)
                            Toast.makeText(context, "Usuario creado correctamente", Toast.LENGTH_SHORT).show() //Temporal
                        }
                    }
                }
                Divider(thickness = 1.dp, modifier = Modifier.padding(16.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    val text1 =
                        if(showLoginForm.value)"¿No tienes cuenta?"
                        else{"¿Ya tienes cuenta?"}
                    val text2 =
                        if(showLoginForm.value)"Registrarse"
                        else{"Inicia sesión"}
                    Text(text = text1)
                    Text(text = text2,
                        modifier = Modifier
                            .clickable { showLoginForm.value = !showLoginForm.value }
                            .padding(start = 5.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit ={email, password ->}
) {
    val email = rememberSaveable{
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val valido = remember(email.value,password.value){
        email.value.trim().isNotEmpty() &&
                password.value.trim().isNotEmpty()
    }
    val CustomTeal = Color(0xFFA6FF80)
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(CustomTeal)) {
        EmailInput(
            emailState= email
        )
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        SubmitButton(
            textId = if(isCreateAccount)"Crear cuenta" else "Iniciar Sesion",
            inputValido = valido
        ){
            onDone(email.value.trim(),password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButton(
    textId: String,
    inputValido:Boolean,
    onClic: ()->Unit
) {
    Button(onClick = onClic,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValido,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ){
        Text(text=textId,
            modifier = Modifier
                .padding(5.dp)
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    val visualTransformation = if(passwordVisible.value)
        VisualTransformation.None
    else PasswordVisualTransformation()
    OutlinedTextField(
        value =passwordState.value,
        onValueChange ={passwordState.value = it},
        label={Text(text=labelId)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .background(Color.White),
        visualTransformation =visualTransformation,
        trailingIcon = {
            if(passwordState.value.isNotBlank()){
                PasswordVisibleIcon(passwordVisible)
            }else null
        }
    )
}

@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>
) {
    val image =
        if(passwordVisible.value)
            Icons.Default.VisibilityOff
        else
            Icons.Default.Visibility
    IconButton(onClick = {
        passwordVisible.value =!passwordVisible.value
    }) {
        Icon(
            imageVector = image,
            contentDescription = "")
    }
}

@Composable
fun EmailInput(emailState: MutableState<String>,
    labelId : String = "Email"
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    isSingLine: Boolean = true,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {valueState.value = it},
        label = {Text(text=labelId)},
        singleLine = isSingLine,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .background(Color.White),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}