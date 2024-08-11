package pe.idat.apppatitas_compose.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.idat.apppatitas_compose.auth.view.cabeceraRegistro

@Composable
fun voluntarioScreen(){
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState)}) {
        paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
            cabeceraRegistro()
            Spacer(modifier = Modifier.height(10.dp))
            formularioVoluntario()
        }
    }
}

@Composable
fun cabaceraVoluntario(){
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(imageVector = Icons.Filled.Pets, contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp))
        Text(text = "UNETE A NOSOTROS",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
fun formularioVoluntario(){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        lblTerminosCondiciones()
        cbTerminosCondiciones()
        btnRegistrarVoluntario()
    }
}

@Composable
fun lblTerminosCondiciones(){
    Text(text = "", fontSize = 12.sp, textAlign = TextAlign.Justify)
}

@Composable
fun cbTerminosCondiciones(){
    Row {
        Checkbox(checked = false, onCheckedChange = {it})
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "Aceptar términos y condiciones",
            Modifier.padding(top = 12.dp), fontSize = 12.sp)
    }
}
@Composable
fun btnRegistrarVoluntario(){
    Button(onClick = { /*TODO*/ }, Modifier.fillMaxWidth()) {
        Text(text = "Registrar Voluntario")
    }
}