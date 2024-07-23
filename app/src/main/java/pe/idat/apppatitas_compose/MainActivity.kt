package pe.idat.apppatitas_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.idat.apppatitas_compose.auth.view.loginScreen
import pe.idat.apppatitas_compose.auth.view.registroScreen
import pe.idat.apppatitas_compose.core.ruta.RutaPatitas
import pe.idat.apppatitas_compose.ui.theme.ApppatitascomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApppatitascomposeTheme {
                val navigation = rememberNavController()
                NavHost(navController = navigation,
                    startDestination = RutaPatitas.loginScreen.path,
                    builder = {
                        composable(RutaPatitas.loginScreen.path){
                            loginScreen()
                        }
                        composable(RutaPatitas.registroScreen.path){
                            registroScreen()
                        }
                    })
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApppatitascomposeTheme {

    }
}