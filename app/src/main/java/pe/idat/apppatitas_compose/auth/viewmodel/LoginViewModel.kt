package pe.idat.apppatitas_compose.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _usuario = MutableLiveData<String>()
    val usuario: LiveData<String> = _usuario
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private var _botonLoginHabilitado = MutableLiveData<Boolean>()
    var botonLoginHabilitado : LiveData<Boolean> = _botonLoginHabilitado

    fun onValueChanged(usuario: String, password: String){
        _usuario.value = usuario
        _password.value = password
        _botonLoginHabilitado.value = habilitarBotonLogin(usuario, password)
    }
    fun habilitarBotonLogin(usuario:String, password: String) = usuario.length > 2
            && password.length > 2
}