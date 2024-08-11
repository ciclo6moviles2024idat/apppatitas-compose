package pe.idat.apppatitas_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pe.idat.apppatitas_compose.auth.view.loginScreen
import pe.idat.apppatitas_compose.auth.view.registroScreen
import pe.idat.apppatitas_compose.auth.viewmodel.LoginViewModel
import pe.idat.apppatitas_compose.auth.viewmodel.RegistroViewModel
import pe.idat.apppatitas_compose.core.ruta.RutaPatitas
import pe.idat.apppatitas_compose.home.view.homeScreen
import pe.idat.apppatitas_compose.home.viewmodel.MascotaViewModel
import pe.idat.apppatitas_compose.home.viewmodel.VoluntarioViewModel
import pe.idat.apppatitas_compose.ui.theme.ApppatitascomposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val registroViewModel: RegistroViewModel by viewModels()
    private val mascotaViewModel: MascotaViewModel by viewModels()
    private val voluntarioViewModel: VoluntarioViewModel by viewModels()
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
                            loginScreen(loginViewModel, navigation)
                        }
                        composable(RutaPatitas.registroScreen.path){
                            registroScreen(registroViewModel, navigation)
                        }
                        composable(RutaPatitas.homeScreen.path){
                            homeScreen(mascotaViewModel, voluntarioViewModel)
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