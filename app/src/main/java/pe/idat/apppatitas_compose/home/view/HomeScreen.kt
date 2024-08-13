package pe.idat.apppatitas_compose.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pe.idat.apppatitas_compose.R
import pe.idat.apppatitas_compose.core.ruta.RutaPatitas
import pe.idat.apppatitas_compose.core.util.MenuItem
import pe.idat.apppatitas_compose.home.viewmodel.MascotaViewModel
import pe.idat.apppatitas_compose.home.viewmodel.VoluntarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(mascotaViewModel: MascotaViewModel, voluntarioViewModel: VoluntarioViewModel){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    ModalNavigationDrawer(drawerState = drawerState,
        drawerContent = {
            DrawerContent(items = opcionesMenu(), onItemClick = {
                item ->
                coroutineScope.launch {
                    drawerState.close()
                }
                when(item.titulo){
                    "Mascotas" -> navController.navigate(RutaPatitas.mascotaScreen.path)
                    "Voluntario" -> navController.navigate(RutaPatitas.voluntarioScreen.path)
                }
            }, mascotaViewModel)
        },
        content = {
            Column {
                TopAppBar(title = { Text(text = "Patitas APP") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor =  Color.Black
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    })
                NavHost(navController = navController,
                    startDestination = RutaPatitas.mascotaScreen.path) {
                    composable(RutaPatitas.mascotaScreen.path){ mascotaScreen(mascotaViewModel)}
                    composable(RutaPatitas.voluntarioScreen.path){ voluntarioScreen() }
                }
            }
        })
}


@Composable
fun DrawerContent(items: List<MenuItem>,
                  onItemClick: (MenuItem) -> Unit,
                  mascotaViewModel: MascotaViewModel){
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()) {
        DrawerHeader(mascotaViewModel)
        Spacer(modifier = Modifier.height(8.dp))
        items.forEach { item ->
            DrawerMenuItem(item = item, onItemClick = onItemClick)
        }
    }
}

@Composable
fun DrawerHeader(mascotaViewModel: MascotaViewModel){
    val persona by mascotaViewModel.persona.observeAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.imgperfil),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape))
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            persona?.let {
                values ->
                Text(text = values.nombres, fontWeight = FontWeight.Bold)
                Text(text = values.email, color = Color.Gray)
            }

        }
    }
}

@Composable
fun DrawerMenuItem(
    item: MenuItem,
    onItemClick: (MenuItem) -> Unit
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(item) }
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = item.icon, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = item.titulo)
    }
}

fun opcionesMenu(): List<MenuItem>{
    return listOf(
        MenuItem(Icons.Default.Pets, "Mascotas"),
        MenuItem(Icons.Default.People, "Voluntario")
    )
}