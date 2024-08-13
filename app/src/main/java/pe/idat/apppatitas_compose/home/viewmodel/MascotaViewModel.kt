package pe.idat.apppatitas_compose.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.apppatitas_compose.core.bd.PersonaEntity
import pe.idat.apppatitas_compose.home.data.network.response.MascotaResponse
import pe.idat.apppatitas_compose.home.domain.EliminarPersonaUseCase
import pe.idat.apppatitas_compose.home.domain.MascotaUseCase
import pe.idat.apppatitas_compose.home.domain.ObtenerPersonaUseCase
import javax.inject.Inject

@HiltViewModel
class MascotaViewModel @Inject constructor(
    private val mascotaUseCase: MascotaUseCase,
    private val obtenerPersonaUseCase: ObtenerPersonaUseCase,
    private val eliminarPersonaUseCase: EliminarPersonaUseCase
) : ViewModel() {
    private val _mascotaResponse = MutableLiveData<List<MascotaResponse>>()
    val mascotaResponse: LiveData<List<MascotaResponse>> = _mascotaResponse
    val persona: LiveData<PersonaEntity> = obtenerPersonaUseCase()

    init {
        listarMascotas()
    }
    fun listarMascotas(){
        viewModelScope.launch {
            val response  = mascotaUseCase()
            _mascotaResponse.value = response
        }
    }
    fun eliminarPersona(){
        viewModelScope.launch {
            eliminarPersonaUseCase()
        }
    }
}