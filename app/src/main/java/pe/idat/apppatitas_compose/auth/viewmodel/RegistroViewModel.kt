package pe.idat.apppatitas_compose.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.idat.apppatitas_compose.auth.data.network.response.RegistroResponse
import pe.idat.apppatitas_compose.auth.domain.RegistroUseCase
import pe.idat.apppatitas_compose.core.util.Evento
import javax.inject.Inject


class RegistroViewModel @Inject constructor(
    private val registroUseCase: RegistroUseCase) : ViewModel() {

    private val _nombres = MutableLiveData<String>()
    val nombres : LiveData<String> = _nombres
    private val _apellidos = MutableLiveData<String>()
    val apellidos : LiveData<String> = _apellidos
    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email
    private val _celular = MutableLiveData<String>()
    val celular : LiveData<String> = _celular
    private val _usuario = MutableLiveData<String>()
    val usuario : LiveData<String> = _usuario
    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _registroResponse = MutableLiveData<Evento<RegistroResponse>>()
    val registroResponse: LiveData<Evento<RegistroResponse>> = _registroResponse



}