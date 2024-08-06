package pe.idat.apppatitas_compose.core.util

open class Evento<out T>(private val contenido: T) {

    var hasBeenHandled = false
        private set

    fun obtenerContenidoSiCambio(): T?{
        return if (hasBeenHandled){
            null
        }else{
            hasBeenHandled = true
            contenido
        }
    }

    fun obtenerContenido(): T = contenido

}