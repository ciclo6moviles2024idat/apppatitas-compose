package pe.idat.apppatitas_compose.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.apppatitas_compose.core.bd.PersonaEntity
import pe.idat.apppatitas_compose.home.data.network.request.VoluntarioRequest
import pe.idat.apppatitas_compose.home.data.network.response.VoluntarioResponse
import pe.idat.apppatitas_compose.home.domain.ActualizarPersonaUseCase
import pe.idat.apppatitas_compose.home.domain.ObtenerPersonaUseCase
import pe.idat.apppatitas_compose.home.domain.VoluntarioUseCase
import javax.inject.Inject

@HiltViewModel
class VoluntarioViewModel @Inject constructor(
    private val voluntarioUseCase: VoluntarioUseCase,
    private val actualizarPersonaUseCase: ActualizarPersonaUseCase,
    private val obtenerPersonaUseCase: ObtenerPersonaUseCase) : ViewModel() {

    private val _voluntarioResponse = MutableLiveData<VoluntarioResponse>()
    val voluntarioResponse: LiveData<VoluntarioResponse> = _voluntarioResponse
    val persona: LiveData<PersonaEntity> = obtenerPersonaUseCase()


    fun registrarVoluntario(){
        viewModelScope.launch {
            val response  = voluntarioUseCase(
                VoluntarioRequest(persona.value!!.id)
            )
            _voluntarioResponse.value = response
            if(response.rpta){
                actualizarPersonaUseCase(
                    PersonaEntity(persona.value!!.id,
                        persona.value!!.nombres,
                        persona.value!!.apellidos,
                        persona.value!!.email,
                        persona.value!!.celular,
                        persona.value!!.usuario,
                        persona.value!!.password,
                        "1")
                )
            }
        }
    }

}