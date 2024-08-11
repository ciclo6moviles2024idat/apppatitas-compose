package pe.idat.apppatitas_compose.core.ruta

sealed class RutaPatitas(val path: String) {
    object loginScreen: RutaPatitas("loginScreen")
    object registroScreen: RutaPatitas("registroScreen")
    object homeScreen: RutaPatitas("homeScreen")
    object mascotaScreen: RutaPatitas("mascotaScreen")
    object voluntarioScreen: RutaPatitas("voluntarioScreen")
}