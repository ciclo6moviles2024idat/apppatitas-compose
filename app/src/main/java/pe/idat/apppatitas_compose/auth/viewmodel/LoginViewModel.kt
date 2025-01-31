package pe.idat.apppatitas_compose.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.apppatitas_compose.auth.data.network.request.LoginRequest
import pe.idat.apppatitas_compose.auth.data.network.response.LoginResponse
import pe.idat.apppatitas_compose.auth.domain.LoginUseCase
import pe.idat.apppatitas_compose.auth.domain.RegistroPersonaUseCase
import pe.idat.apppatitas_compose.core.bd.PersonaEntity
import pe.idat.apppatitas_compose.core.util.Evento
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registroPersonaUseCase: RegistroPersonaUseCase) : ViewModel() {

    private val _usuario = MutableLiveData<String>()
    val usuario: LiveData<String> = _usuario
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private var _botonLoginHabilitado = MutableLiveData<Boolean>()
    var botonLoginHabilitado : LiveData<Boolean> = _botonLoginHabilitado
    private val _loginResponse = MutableLiveData<Evento<LoginResponse>>()
    val loginResponse: LiveData<Evento<LoginResponse>> = _loginResponse

    fun onValueChanged(usuario: String, password: String){
        _usuario.value = usuario
        _password.value = password
        _botonLoginHabilitado.value = habilitarBotonLogin(usuario, password)
    }
    fun loginUsuarioPassword(){
        viewModelScope.launch {
            val response = loginUseCase(
                LoginRequest(usuario.value!!, password.value!!)
            )
            _loginResponse.value = Evento(response)
            registroPersonaUseCase(PersonaEntity(
                response.idpersona.toInt(),
                response.nombres, response.apellidos, response.email,
                response.celular, response.usuario, response.password,
                response.esvoluntario))
        }
    }

    fun habilitarBotonLogin(usuario:String, password: String) = usuario.length > 2
            && password.length > 2
}